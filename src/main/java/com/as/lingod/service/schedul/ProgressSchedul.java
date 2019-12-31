package com.as.lingod.service.schedul;

import com.as.lingod.domain.FaLinkDetail;
import com.as.lingod.domain.FaLinkPool;
import com.as.lingod.domain.FaSataWork;
import com.as.lingod.service.FaLinkDetailService;
import com.as.lingod.service.FaLinkPoolService;
import com.as.lingod.service.FaSataWorkService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by brander on 2019/12/28
 */
@EnableScheduling
@Configuration
public class ProgressSchedul {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FaLinkPoolService linkPoolService;

    @Autowired
    private FaLinkDetailService linkDetailService;

    @Autowired
    private FaSataWorkService faSataWorkService;

    private final String J_TIME = "jtime";
    private final String XIAN_BIE = "xianbie";


    /**
     * 自动捞取数据操作
     * 10/30 2/20 * * * ?
     */
    @Scheduled(cron = "0/30 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void startProcessing() {
        Gson gson = new Gson();
        logger.info("startProcessing");
        //获取没有处理的数据  或许线别，获取相同时间
        Wrapper<FaSataWork> w = new EntityWrapper<>();
        Map<String, Object> map = new HashMap<>(2);
        map.put("`out`", false);
        w.allEq(map).orderAsc(Lists.newArrayList(J_TIME, XIAN_BIE));
        List<FaSataWork> list = faSataWorkService.selectList(w);
        if (CollectionUtils.isEmpty(list)) {
            //目前无数据需要记录，未开线
            logger.error("[目前无数据需要记录，未开线]");
            return;
        }


        logger.info("[开始计算数据]");
        //相同时间合并
        Map<Long, List<FaSataWork>> faDateMap = new HashMap<>(10);
        //先过滤出线别,讲相同线别的数据塞入
        for (FaSataWork faSataWork : list) {
            Date date = faSataWork.getJtime();
            long time = date.getTime();
            if (CollectionUtils.isEmpty(faDateMap.get(time))) {
                faDateMap.put(time, Lists.newArrayList(faSataWork));
            } else {
                faDateMap.get(time).add(faSataWork);
            }
        }

        //遍历map 时间相同的分成数组，时间相同的数据按照xianbie-list合并
        Iterator<Map.Entry<Long, List<FaSataWork>>> longEntry = faDateMap.entrySet().iterator();
        while (longEntry.hasNext()) {
            Map.Entry<Long, List<FaSataWork>> longDa = longEntry.next();
            List<FaSataWork> tempLongList = longDa.getValue();
            //获取相同线的数据合并
            //map 记录线别数据
            Map<String, List<FaSataWork>> faMap = new HashMap<>(10);
            //先过滤出线别,讲相同线别的数据塞入
            for (FaSataWork faSataWork : tempLongList) {
                String name = faSataWork.getXianbie();
                if (CollectionUtils.isEmpty(faMap.get(name))) {
                    faMap.put(name, Lists.newArrayList(faSataWork));
                } else {
                    faMap.get(name).add(faSataWork);
                }
            }

            //分离数据
            Iterator<Map.Entry<String, List<FaSataWork>>> entry = faMap.entrySet().iterator();
            // --map-> xianbie:list --list-> group:
            Map<String, Map<String, List<FaSataWork>>> tempFaMap = new HashMap<>(10);
            while (entry.hasNext()) {
                Map.Entry<String, List<FaSataWork>> da = entry.next();
                String tempXB = da.getKey();
                List<FaSataWork> tempList = da.getValue();
                //查分组别相同，时间不同的数据
                Map<String, List<FaSataWork>> faSataWorkMap = new HashMap<>(10);
                for (FaSataWork faSataWork : tempList) {
                    //相同组别的数据
                    String group = faSataWork.getGroup();
                    if (CollectionUtils.isEmpty(faSataWorkMap.get(group))) {
                        faSataWorkMap.put(group, Lists.newArrayList(faSataWork));
                    } else {
                        faSataWorkMap.get(group).add(faSataWork);
                    }
                }
                tempFaMap.put(tempXB, faSataWorkMap);
            }
            //结果为：线别（列表）：组别：具体数据（列表）
            logger.info(gson.toJson(tempFaMap));
            //过滤出，每次只有多线，多组，但是每组只有一条的数据
            List<Map<String, List<FaSataWork>>> mapList = new ArrayList<>(10);

            Iterator<Map.Entry<String, Map<String, List<FaSataWork>>>> tempEntry = tempFaMap.entrySet().iterator();
            //组装结果：线别相同且时间相同的数据放到一个map里，不同的分到list中
            while (tempEntry.hasNext()) {
                Map.Entry<String, Map<String, List<FaSataWork>>> da = tempEntry.next();
                String xb = da.getKey();
                Map<String, List<FaSataWork>> tempMap = da.getValue();
                Iterator<Map.Entry<String, List<FaSataWork>>> tempMapEntry = tempMap.entrySet().iterator();
                //添加数据
                while (tempMapEntry.hasNext()) {
                    Map.Entry<String, List<FaSataWork>> tempDa = tempMapEntry.next();
                    String group = tempDa.getKey();
                    List<FaSataWork> faSataWorks = tempDa.getValue();
                    //相同xianbie的数据
                    for (FaSataWork faSataWork : faSataWorks) {
                        //时间相同，则放到一个数据里，不同则添加list
                        Map<String, List<FaSataWork>> faListMap = new HashMap<>(1);
                        faListMap.put(xb, Lists.newArrayList(faSataWork));
                        mapList.add(faListMap);
                    }
                }

            }
        }

        //组装为list后的数据 xianbie
        // :list
//        logger.info("[组装为list后的数据]{}", gson.toJson(mapList));


//        saveData(faMap);

    }

    /**
     * 保存数据
     *
     * @param faMap Map<String, List<FaSataWork>> faMap
     */
    private void saveData(Map<String, List<FaSataWork>> faMap) {
        //用于记录要存的数据
        List<FaLinkPool> poolList = new ArrayList<>();
        //遍历数据计算
        Iterator<Map.Entry<String, List<FaSataWork>>> entry = faMap.entrySet().iterator();
        while (entry.hasNext()) {
            Map.Entry<String, List<FaSataWork>> data = entry.next();
            String name = data.getKey();
            //获取上一条link数据
            FaLinkPool lastLink = linkPoolService.getLastLink(name);
            if (lastLink == null) {
                lastLink = new FaLinkPool();
                //没有记录数据，则上一笔数据为 0
                lastLink.setDefectiveRate("0");
                lastLink.setTeamPerformance("0");
                lastLink.setXianbie(name);
                lastLink.setTotalPass(0);
                lastLink.setTotalFail(0);
                lastLink.setTotal(0);
                lastLink.setAreaTotal(0);
                lastLink.setAreaFail(0);
            }
            //获取上一条分组的频率数据
            Integer linkId = lastLink.getId();
            Map<String, Object> linkMap = new HashMap<>(1);
            linkMap.put("link_id", linkId);
            List<FaLinkDetail> lastLinkDetails = linkDetailService.selectByMap(linkMap);

            List<FaSataWork> ldata = data.getValue();
            Integer totalPass = 0;
            Integer totalFail = 0;
            Integer totalPeo = 0;
            BigDecimal totalGEff = BigDecimal.ZERO;
            //用于保存的记录数据
            List<FaLinkDetail> linkList = new ArrayList<>();

            //计算上面数据 相同线别的不同组别
            for (FaSataWork ldatum : ldata) {
                //合并每组的好品和坏品数据
                totalPass = totalPass + ldatum.getPass();
                totalFail = totalFail + ldatum.getFail();
                //如果为空则赋值为空值
                if (CollectionUtils.isEmpty(lastLinkDetails)) {
                    //区间默认值都为 0
                    logger.info("[无上一条数据记录]");
                    FaLinkDetail linkDetail = new FaLinkDetail();
                    linkDetail.setAreaPass(0);
                    linkDetail.setAreaSPass(0);
                    linkDetail.setAreaEff(BigDecimal.ZERO);
                    linkDetail.setCreateDate(ldatum.getJtime());
                    linkDetail.setPass(ldatum.getPass());
                    linkDetail.setFail(ldatum.getFail());
                    linkDetail.setGroup(ldatum.getGroup());
                    linkDetail.setPeople(ldatum.getPeople());
                    linkList.add(linkDetail);
                } else {
                    //有上一条数据
                    for (FaLinkDetail laskLinkDetail : lastLinkDetails) {
                        String group = laskLinkDetail.getGroup().trim();
                        //组别相同，计算，每个 list 中只有一个单独的组别，不会同时有多个相同组别进入
                        if (group.equals(ldatum.getGroup().trim())) {
                            FaLinkDetail linkDetail = new FaLinkDetail();
                            //算出每组的区间产量 当前-上一笔
                            int lastPass = laskLinkDetail.getPass();
                            //组区间好品数
                            Integer areaPass = ldatum.getPass() - lastPass;
                            //算出毫秒数
                            long time = ldatum.getJtime().getTime() - laskLinkDetail.getCreateDate().getTime();
                            //算出为小时数据
                            String areaTime = "" + time / (1000 * 60 * 60);
                            //TODO 计算标准产出 先不算
                            Integer peo = 0;
                            BigDecimal areaS = BigDecimal.ZERO.multiply(new BigDecimal("" + peo));
                            //单个组别的效率： 区间产出量/区间标准产出(区间时(两时间段相减/24)*每小时产出)
                            BigDecimal groupEff = new BigDecimal("" + areaPass)
                                    .divide(areaS, BigDecimal.ROUND_HALF_UP, 4);
                            // totalGEff = totalGEff.add(groupEff);

                            linkDetail.setAreaPass(areaPass);
                            linkDetail.setAreaSPass(areaS.intValue());
                            linkDetail.setAreaEff(groupEff);
                            linkDetail.setCreateDate(ldatum.getJtime());
                            linkDetail.setPass(ldatum.getPass());
                            linkDetail.setFail(ldatum.getFail());
                            linkDetail.setGroup(ldatum.getGroup());
                            linkDetail.setPeople(ldatum.getPeople());
                            linkList.add(linkDetail);
                        }
                    }
                }
                //若 mc 数据是对的，则直接相加
                totalGEff = totalGEff.add(new BigDecimal(ldatum.getEfficiency()));
                totalPeo = totalPeo + ldatum.getPeople();
                //更新状态为已处理
                FaSataWork t = new FaSataWork();
                t.setOut(true);
                Wrapper<FaSataWork> ww = new EntityWrapper<>();
                Map<String, Object> faSataWorkMap = new HashMap<>(1);
                faSataWorkMap.put("id", ldatum.getId());
                faSataWorkMap.put("`out`", 0);
                ww.allEq(faSataWorkMap);
                if (!faSataWorkService.update(t, ww)) {
                    logger.error("[更新记录为已记录失败][{}]", ldatum);
                    throw new RuntimeException("[更新记录为已记录失败]");
                }
            }
            //不同组别的时间相同：
            Date time = ldata.get(0).getJtime();

            Integer total = totalPass + totalFail;
            Integer areaTotal = total - lastLink.getTotal();
            Integer areaFail = totalFail - lastLink.getTotalFail();
            //不良率
            BigDecimal defectiveRate = new BigDecimal("" + areaFail)
                    .divide(new BigDecimal("" + areaTotal), BigDecimal.ROUND_HALF_UP, 4);
            FaLinkPool linkPool = new FaLinkPool();
            linkPool.setDefectiveRate(defectiveRate.stripTrailingZeros().toPlainString());
            linkPool.setCreateDate(time);
            linkPool.setXianbie(name);
            linkPool.setTotalPass(totalPass);
            linkPool.setTotalFail(totalFail);
            linkPool.setTotal(total);
            linkPool.setAreaTotal(areaTotal);
            linkPool.setAreaFail(areaFail);

            //如：((aa1-aa0)/as  +  (ba1-ba0)/bs)  / (ap+bp)
            //团队效率：单个组别效率*是否结业*人力 ++ / 是否结业*人力 ++

            linkPool.setTeamPerformance(
                    totalGEff
                            .divide(new BigDecimal("" + totalPeo)
                                    , BigDecimal.ROUND_HALF_UP, 4)
                            .stripTrailingZeros()
                            .toPlainString());
            //添加数据
            poolList.add(linkPool);
            logger.info("[计算完成结果poolList][{}]", poolList);
            try {
                boolean res = linkPoolService.saveLinkInfo(linkList, linkPool);
                if (!res) {
                    logger.error("[数据未保存错误]");
                    throw new RuntimeException("error");
                }
            } catch (Exception e) {
                logger.error("[数据保存错误]" + ExceptionUtils.getStackTrace(e));
            }


        }
    }

}
