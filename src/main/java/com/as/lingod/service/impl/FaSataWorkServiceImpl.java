package com.as.lingod.service.impl;

import com.as.lingod.dao.FaSataWorkMapper;
import com.as.lingod.domain.FaLinkDetail;
import com.as.lingod.domain.FaLinkPool;
import com.as.lingod.domain.FaSataWork;
import com.as.lingod.service.FaLinkDetailService;
import com.as.lingod.service.FaLinkPoolService;
import com.as.lingod.service.FaSataWorkService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ban123
 * @since 2019-12-29
 */
@Service
public class FaSataWorkServiceImpl extends ServiceImpl<FaSataWorkMapper, FaSataWork> implements FaSataWorkService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FaSataWorkMapper faSataWorkMapper;

    @Autowired
    private FaLinkPoolService faLinkPoolService;
    @Autowired
    private FaLinkDetailService linkDetailService;

    @Autowired
    private FaSataWorkService faSataWorkService;


    /**
     * 获取分组
     *
     * @return r
     */
    @Override
    public List<Integer> getDistinctCount() {
        return faSataWorkMapper.getDistinctCount();
    }

    /**
     * 保存数据
     *
     * @param faMap Map<String, List<FaSataWork>> faMap
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveData(Map<String, List<FaSataWork>> faMap) {
        //用于记录要存的数据
        List<FaLinkPool> poolList = new ArrayList<>();
        //遍历数据计算
        Iterator<Map.Entry<String, List<FaSataWork>>> entry = faMap.entrySet().iterator();
        while (entry.hasNext()) {
            Map.Entry<String, List<FaSataWork>> data = entry.next();
            String name = data.getKey();
            //获取上一条link数据（获取当天 0 点之后的数据）
            FaLinkPool lastLink = faLinkPoolService.getLastLink(name);
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
            //记录结批数据
            Set<FaSataWork> groups = new HashSet<>(10);

            //计算上面数据 相同线别的不同组别
            for (FaSataWork ldatum : ldata) {
                //更新状态为已处理，最后在处理
                FaSataWork t = new FaSataWork();
                t.setOut(true);
                Wrapper<FaSataWork> ww = new EntityWrapper<>();
                Map<String, Object> faSataWorkMap = new HashMap<>(1);
                faSataWorkMap.put("id", ldatum.getId());
                faSataWorkMap.put("`out`", 0);
                ww.allEq(faSataWorkMap);
                //判断是不是结批附加数据
                if (ldatum.getStatus() == 2
                        && ldatum.getPass() == 0
                        && ldatum.getFail() == 0) {
                    if (!faSataWorkService.update(t, ww)) {
                        logger.error("[更新记录为已记录失败][{}]", ldatum);
                        throw new RuntimeException("[更新记录为已记录失败]");
                    }
                    //下一条数据
                    continue;
                }
                // 判断是不是正常结批数据
                if (ldatum.getStatus() == 2 && ldatum.getFail() != 0
                        && ldatum.getPass() != 0) {
                    //如果结批多加一条数据,且不计算不记录
                    groups.add(ldatum);
                }
                //合并每组的好品和坏品数据
                totalPass = totalPass + ldatum.getPass();
                totalFail = totalFail + ldatum.getFail();
                //如果为空则赋值为空值
                if (CollectionUtils.isEmpty(lastLinkDetails)) {
                    //区间默认值都为 0
                    logger.info("[无上一条数据记录]");
                    setDefaultDetail(linkList, ldatum);
                } else {
                    //有上一条数据
                    FaLinkDetail linkDetail = new FaLinkDetail();
                    for (FaLinkDetail laskLinkDetail : lastLinkDetails) {
                        String group = laskLinkDetail.getGroup().trim();
                        //组别相同，计算，每个 list 中只有一个单独的组别，不会同时有多个相同组别进入
                        //且 数据 不为0 ，则为结批另加的数据
                        if (group.equals(ldatum.getGroup().trim())
                                && ldatum.getFail() != 0
                                && ldatum.getPass() != 0) {
                            //算出每组的区间产量 当前-上一笔
                            int lastPass = laskLinkDetail.getPass();
                            //组区间好品数
                            Integer areaPass = ldatum.getPass() - lastPass;
                            //算出毫秒数
                            long time = ldatum.getJtime().getTime() - laskLinkDetail.getCreateDate().getTime();
                            //算出为小时数据
                            String areaTime = "" + time / (1000 * 60 * 60);
                            // 计算标准产出 先不算
                            Integer peo = ldatum.getPeople();
                            BigDecimal areaS = BigDecimal.ZERO.multiply(new BigDecimal("" + peo));
                            //单个组别的效率： 区间产出量/区间标准产出(区间时(两时间段相减/24)*每小时产出)
//                            BigDecimal groupEff = new BigDecimal("" + areaPass)
//                                    .divide(areaS, BigDecimal.ROUND_HALF_UP, 4);
                            // totalGEff = totalGEff.add(groupEff);
                            linkDetail.setAreaPass(areaPass);
                            linkDetail.setAreaSPass(areaS.intValue());
                            linkDetail.setAreaEff(new BigDecimal(ldatum.getEfficiency()));
                            linkDetail.setCreateDate(ldatum.getJtime());
                            linkDetail.setPass(ldatum.getPass());
                            linkDetail.setGroup(ldatum.getGroup());
                            linkDetail.setFail(ldatum.getFail());
                            linkDetail.setPeople(ldatum.getPeople());
                            linkList.add(linkDetail);
                            //只取一条数据
                            break;
                        }
                    }
                    if (linkDetail.getAreaSPass() == null) {
                        setDefaultDetail(linkList, ldatum);
                    }
                }
                //若 mc 数据是对的，则直接相加
                totalGEff = totalGEff.add(new BigDecimal(ldatum.getEfficiency())
                        .multiply(new BigDecimal("" + ldatum.getPeople())));
                totalPeo = totalPeo + ldatum.getPeople();
                //更新为已操作
                if (!faSataWorkService.update(t, ww)) {
                    logger.error("[更新记录为已记录失败][{}]", ldatum);
                    throw new RuntimeException("[更新记录为已记录失败]");
                }
                logger.info("[detail]{}", linkList);
            }
            //不同组别的时间相同：
            Date time = ldata.get(0).getJtime();
            //当前总量
            Integer total = totalPass + totalFail;
            //区间总量
            Integer areaTotal = total - lastLink.getTotal();
            //区间坏品
            Integer areaFail = totalFail - lastLink.getTotalFail();
            //不良率
            BigDecimal defectiveRate = BigDecimal.ZERO;
            if (areaFail != 0) {
                defectiveRate = new BigDecimal("" + areaFail)
                        .divide(new BigDecimal("" + areaTotal), BigDecimal.ROUND_HALF_UP, 4);
            }
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
                    totalGEff.divide(new BigDecimal("" + totalPeo)
                            , BigDecimal.ROUND_HALF_UP, 4)
                            .stripTrailingZeros()
                            .toPlainString());
            poolList.add(linkPool);
            //添加结批数据
            int gpPass = 0;
            int gpFail = 0;
            for (FaSataWork group : groups) {
                gpPass = gpPass + group.getPass();
                gpFail = gpFail + group.getFail();
            }
            if (groups.size() > 0) {
                //设置数据,当为结批时，多记录
                FaLinkPool linkPool2 = new FaLinkPool();
                linkPool2.setDefectiveRate("0");
                linkPool2.setCreateDate(time);
                linkPool2.setXianbie(name);
                linkPool2.setTotalPass(totalPass - gpPass);
                linkPool2.setTotalFail(totalFail - gpFail);
                linkPool2.setTotal(total - gpFail - gpPass);
                linkPool2.setAreaTotal(areaTotal);
                linkPool2.setAreaFail(areaFail);
                linkPool2.setUsed(false);
                linkPool2.setTeamPerformance("0");
                //添加数据
                poolList.add(linkPool2);
            }
            logger.info("[计算完成结果poolList]{}", poolList);
            try {
                boolean res = faLinkPoolService.saveLinkInfo(linkList, poolList);
                if (!res) {
                    logger.error("[数据未保存错误]");
                    throw new RuntimeException("error");
                }
            } catch (Exception e) {
                logger.error("[数据保存错误]" + ExceptionUtils.getStackTrace(e));
            }
        }
    }

    /**
     * 设置默认值
     *
     * @param linkList ll
     * @param ldatum   ld
     */
    private void setDefaultDetail(List<FaLinkDetail> linkList, FaSataWork ldatum) {
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
    }
}
