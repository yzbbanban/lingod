package com.as.lingod.service.schedul;

import com.as.lingod.domain.FaSataWork;
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
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by brander on 2019/12/28
 */
@EnableScheduling
@Configuration
public class ProgressSchedul {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FaSataWorkService faSataWorkService;

    private final String J_TIME = "jtime";
    private final String XIAN_BIE = "xianbie";


    /**
     * 自动捞取数据操作
     * 10/30 2/20 * * * ?
     */
    @Scheduled(cron = "0 0/1 * * * ? ")
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
        Map<Long, List<FaSataWork>> faDateMap = new LinkedHashMap<>(10);
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
        logger.info("[选出时间相同数据]{}", gson.toJson(faDateMap));


        //遍历map 时间相同的分成数组，时间相同的数据按照xianbie-list合并
        Iterator<Map.Entry<Long, List<FaSataWork>>> longEntry = faDateMap.entrySet().iterator();
        List<Map<String, List<FaSataWork>>> mapList = new ArrayList<>();
        while (longEntry.hasNext()) {
            Map.Entry<Long, List<FaSataWork>> longDa = longEntry.next();
            List<FaSataWork> tempLongList = longDa.getValue();
            logger.info("[获取时间相同数据]{}:{}", longDa, gson.toJson(tempLongList));
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
            mapList.add(faMap);
        }

        logger.info("[组装为list后的数据]{}", gson.toJson(mapList));

        for (Map<String, List<FaSataWork>> stringListMap : mapList) {
            try {
                faSataWorkService.saveData(stringListMap);
                Thread.sleep(2000);
            } catch (Exception e) {
                logger.error("[保存失败error]{}", ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
            }
        }

    }


}
