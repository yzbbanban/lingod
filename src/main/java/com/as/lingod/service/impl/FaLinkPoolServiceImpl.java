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
    public boolean saveLinkInfo(List<FaLinkDetail> linkList, FaLinkPool linkPool) {
        //保存数据
        int row = linkPoolMapper.insert(linkPool);
        if (row <= 0) {
            logger.error("[保存记录数据失败]{}", linkPool);
            throw new RuntimeException("error");
        }
        int id = linkPool.getId();
        for (FaLinkDetail linkDetail : linkList) {
            linkDetail.setLinkId(id);
        }
        boolean res = linkDetailService.insertBatch(linkList);
        if (!res) {
            logger.error("[保存记录详情数据失败]{}", linkList);
            throw new RuntimeException("error");
        }
        return true;
    }

}
