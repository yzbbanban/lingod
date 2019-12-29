package com.as.lingod.service.schedul;

import com.as.lingod.domain.FaSataWork;
import com.as.lingod.domain.FaSatatime;
import com.as.lingod.domain.LinkPool;
import com.as.lingod.domain.ProcessingPool;
import com.as.lingod.service.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Lists;
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
    private FaSataWorkService faSataWorkService;


    /**
     * 自动捞取数据操作
     * 10/30 2/20 * * * ?
     */
    @Scheduled(cron = "10 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void startProcessing() {
        logger.info("startProcessing");
        //获取没有处理的数据
        Wrapper<FaSataWork> w = new EntityWrapper<>();
        Map<String, Object> map = new HashMap<>(2);
        map.put("out", "0");
        w.allEq(map).orderAsc(Lists.newArrayList("jtime", "xianbie"));
        List<FaSataWork> list = faSataWorkService.selectList(w);
        if (CollectionUtils.isEmpty(list)) {
            //目前无数据需要记录，未开线
            logger.info("[目前无数据需要记录，未开线]");
            return;
        }
        //获取相同线的数据合并
        //map 记录线别数据
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
        List<LinkPool> poolList = new ArrayList<>();
        //遍历数据计算
        Iterator<Map.Entry<String, List<FaSataWork>>> entry = faMap.entrySet().iterator();
        while (entry.hasNext()) {
            Map.Entry<String, List<FaSataWork>> data = entry.next();
            String name = data.getKey();
            //获取上一条link数据
            LinkPool lastLink = linkPoolService.getLastLink(name);
            //获取上一条数据
            if (lastLink == null) {
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

            List<FaSataWork> ldata = data.getValue();
            Integer totalPass = 0;
            Integer totalFail = 0;
            //计算上面数据 相同线别的不同组别
            for (FaSataWork ldatum : ldata) {
                totalPass = totalPass + ldatum.getPass();
                totalFail = totalFail + ldatum.getFail();
                //累计

            }
            //不同组别的时间相同：
            Date time = ldata.get(0).getJtime();

            Integer total = totalPass + totalFail;
            Integer areaTotal = total - lastLink.getTotal();
            Integer areaFail = totalFail - lastLink.getTotalFail();
            //不良率
            BigDecimal defectiveRate = new BigDecimal("" + areaFail)
                    .divide(new BigDecimal("" + areaTotal), BigDecimal.ROUND_HALF_UP, 4);
            LinkPool linkPool = new LinkPool();
            linkPool.setDefectiveRate(defectiveRate.stripTrailingZeros().toPlainString());
            linkPool.setCreateDate(time);
            linkPool.setXianbie(name);
            linkPool.setTotalPass(totalPass);
            linkPool.setTotalFail(totalFail);
            linkPool.setTotal(total);
            linkPool.setAreaTotal(areaTotal);
            linkPool.setAreaFail(areaFail);

            //单个组别的效率： 区间产出量/区间标准产出(区间时(两时间段相减/24)*每小时产出)
            //团队效率：单个组别效率*是否结业*人力 ++ / 是否结业*人力 ++
            //如：((aa1-aa0)/as  +  (ba1-ba0)/bs)  / (ap+bp)

            linkPool.setTeamPerformance("");
            //添加数据
            poolList.add(linkPool);

        }


    }


}
