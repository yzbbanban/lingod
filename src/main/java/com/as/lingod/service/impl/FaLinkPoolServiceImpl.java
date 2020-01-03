package com.as.lingod.service.impl;

import com.as.lingod.dao.FaLinkPoolMapper;
import com.as.lingod.domain.FaLinkDetail;
import com.as.lingod.domain.FaLinkPool;
import com.as.lingod.service.FaLinkDetailService;
import com.as.lingod.service.FaLinkPoolService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ban123
 * @since 2019-12-28
 */
@Service
public class FaLinkPoolServiceImpl extends ServiceImpl<FaLinkPoolMapper, FaLinkPool> implements FaLinkPoolService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private FaLinkDetailService linkDetailService;

    @Autowired
    private FaLinkPoolMapper linkPoolMapper;

    /**
     * 获取去线别上一条数据
     *
     * @param name n
     * @return r
     */
    @Override
    public FaLinkPool getLastLink(String name) {
        return linkPoolMapper.getLastLink(name);
    }

    /**
     * 保存数据
     *
     * @param linkList l
     * @param linkPool l
     * @return r
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveLinkInfo(List<FaLinkDetail> linkList, List<FaLinkPool> linkPool) {
        //保存数据
        if (CollectionUtils.isEmpty(linkList) || CollectionUtils.isEmpty(linkPool)) {
            logger.error("[保存记录数据失败][数据为空]");
            throw new RuntimeException("数据为空error");
        }
        int id = 0;
        int id2 = 0;
        Date time = null;
        for (FaLinkPool faLinkPool : linkPool) {
            int row = linkPoolMapper.insert(faLinkPool);
            if (row <= 0) {
                logger.error("[保存记录数据失败]{}", linkPool);
                throw new RuntimeException("error");
            }
            time = faLinkPool.getCreateDate();
        }
        id = linkPool.get(0).getId();
        for (FaLinkDetail linkDetail : linkList) {
            linkDetail.setLinkId(id);
        }
        //丢一条结批数据
        if (linkPool.size() > 1) {
            id2 = linkPool.get(1).getId();
            FaLinkDetail linkDetail = new FaLinkDetail();
            linkDetail.setAreaPass(0);
            linkDetail.setAreaSPass(0);
            linkDetail.setAreaEff(BigDecimal.ZERO);
            linkDetail.setCreateDate(time);
            linkDetail.setPass(0);
            linkDetail.setFail(0);
            linkDetail.setGroup("");
            linkDetail.setLinkId(id2);
            linkDetail.setPeople(0);
            linkList.add(linkDetail);
        }
        boolean res = linkDetailService.insertBatch(linkList);
        if (!res) {
            logger.error("[保存记录详情数据失败]{}", linkList);
            throw new RuntimeException("保存记录详情数据失败error");
        }
        return true;
    }

}
