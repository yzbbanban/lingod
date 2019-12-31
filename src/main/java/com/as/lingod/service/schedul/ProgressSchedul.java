package com.as.lingod.service.schedul;

import java.util.Date;

import com.as.lingod.domain.*;
import com.as.lingod.service.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Lists;
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
    private LinkPoolService linkPoolService;

    @Autowired
    private LinkDetailService linkDetailService;

    @Autowired
    private FaSataWorkService faSataWorkService;


    /**
     * 自动捞取数据操作
     * 10/30 2/20 * * * ?
     */
    @Scheduled(cron = "0/30 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void startProcessing() {
        logger.info("startProcessing");
        //获取没有处理的数据
        Wrapper<FaSataWork> w = new EntityWrapper<>();
        Map<String, Object> map = new HashMap<>(2);
        map.put("`out`", false);
        w.allEq(map).orderAsc(Lists.newArrayList("jtime"));
        List<FaSataWork> list = faSataWorkService.selectList(w);
        if (CollectionUtils.isEmpty(list)) {
            //目前无数据需要记录，未开线
            logger.error("[目前无数据需要记录，未开线]");
            return;
        }
        //获取相同线的数据合并
        //map 记录线别数据
        logger.info("[开始计算数据]");
        Map<String, List<FaSataWork>> faMap = new HashMap<>();
        //先过滤出线别,讲相同线别的数据塞入
        for (FaSataWork faSataWork : list) {
            String name = faSataWork.getXianbie();
            if (CollectionUtils.isEmpty(faMap.get(name))) {
                faMap.put(name, Lists.newArrayList(faSataWork));
            } else {
                faMap.get(name).add(faSataWork);
            }
        }

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
            //如果为空则赋值为空值
            if (CollectionUtils.isEmpty(lastLinkDetails)) {
                FaLinkDetail linkDetail = new FaLinkDetail();
                linkDetail.setId(0);
                linkDetail.setLinkId(0);
                linkDetail.setAreaPass(0);
                linkDetail.setAreaSPass(0);
                linkDetail.setAreaEff(new BigDecimal("0"));
                linkDetail.setCreateDate(lastLink.getCreateDate());
                linkDetail.setPass(0);
                linkDetail.setFail(0);
                linkDetail.setGroup("");
                lastLinkDetails.add(linkDetail);
            }

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
                if (CollectionUtils.isEmpty(lastLinkDetails)) {
                    //区间默认值都为 0
                    logger.info("[无上一条数据记录]");
                } else {
                    for (FaLinkDetail laskLinkDetail : lastLinkDetails) {
                        String group = laskLinkDetail.getGroup();
                        //组别相同，计算，每个 list 中只有一个单独的组别，不会同时有多个相同组别进入
                        if (group.equals(ldatum.getGroup())) {
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
                            linkList.add(laskLinkDetail);
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
                ww.allEq(map);
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
