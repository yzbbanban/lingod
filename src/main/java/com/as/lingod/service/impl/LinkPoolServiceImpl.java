package com.as.lingod.service.impl;

import com.as.lingod.domain.LinkPool;
import com.as.lingod.dao.LinkPoolMapper;
import com.as.lingod.service.LinkPoolService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
