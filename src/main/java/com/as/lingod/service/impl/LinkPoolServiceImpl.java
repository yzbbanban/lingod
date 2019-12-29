package com.as.lingod.service.impl;

import com.as.lingod.domain.LinkDetail;
import com.as.lingod.domain.LinkPool;
import com.as.lingod.dao.LinkPoolMapper;
import com.as.lingod.service.LinkDetailService;
import com.as.lingod.service.LinkPoolService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class LinkPoolServiceImpl extends ServiceImpl<LinkPoolMapper, LinkPool> implements LinkPoolService {

    @Autowired
    private LinkPoolMapper linkPoolMapper;
    @Autowired
    private LinkDetailService linkDetailService;

    /**
     * 获取去线别上一条数据
     *
     * @param name n
     * @return r
     */
    @Override
    public LinkPool getLastLink(String name) {
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
    public boolean saveLinkInfo(List<LinkDetail> linkList, LinkPool linkPool) {
        //保存数据
        int row = linkPoolMapper.insert(linkPool);
        if (row <= 0) {
            throw new RuntimeException("error");
        }
        int id = linkPool.getId();
        for (LinkDetail linkDetail : linkList) {
            linkDetail.setLinkId(id);
        }
        boolean res = linkDetailService.insertBatch(linkList);
        if (!res) {
            throw new RuntimeException("error");
        }
        return true;
    }
}
