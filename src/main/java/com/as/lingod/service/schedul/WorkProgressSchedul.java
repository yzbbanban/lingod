package com.as.lingod.service.schedul;

import com.as.lingod.domain.FaSatatime;
import com.as.lingod.domain.ProcessingPool;
import com.as.lingod.service.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brander on 2019/12/28
 */
@EnableScheduling
@Configuration
public class WorkProgressSchedul {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProcessingPoolService processingPoolService;

    @Autowired
    private FaSatapushService faSatapushService;

    @Autowired
    private FaSatatimeService faSatatimeService;

    /**
     * 自动捞取数据操作
     * 10/30 2/20 * * * ?
     */
    @Scheduled(cron = "10 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void startWorking() {
        logger.info("startWorking");
        Wrapper<FaSatatime> wrapper = new EntityWrapper<>();
        Map<String, Object> map = new HashMap<>(1);
        map.put("is_out", 0);
        wrapper.allEq(map);
        List<FaSatatime> list = faSatatimeService.selectList(wrapper);
        if (!CollectionUtils.isEmpty(list)) {
            logger.info("====>" + list);
        }
        for (FaSatatime faSatatime : list) {
            String batch = faSatatime.getBatch();
            //获取 单号的线别


            ProcessingPool t = new ProcessingPool();
            t.setDevice(faSatatime.getDevice());
            t.setBatch(faSatatime.getBatch());
            t.setEfficiency("");
            t.setPeople(12);
            t.setLinkId(1);
            t.setPass(faSatatime.getPass());
            t.setFail(faSatatime.getFail());
            t.setCreateDate(faSatatime.getCreateDate());

            if (!processingPoolService.insert(t)) {
                logger.error("[插入pool数据失败]");
                throw new RuntimeException();
            }

            FaSatatime fa = new FaSatatime();
            fa.setOut(true);
            Wrapper<FaSatatime> w = new EntityWrapper<>();
            Map<String, Object> m = new HashMap<>(2);
            map.put("is_out", 0);
            map.put("id", faSatatime.getId());
            wrapper.allEq(m);
            //只更新为 0 的
            boolean row = faSatatimeService.update(fa, w);
            if (!row) {
                logger.error("[更新状态数据失败]");
                throw new RuntimeException();
            }

        }

    }


}
