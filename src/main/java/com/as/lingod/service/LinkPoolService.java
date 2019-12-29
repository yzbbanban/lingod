package com.as.lingod.service;

import com.as.lingod.domain.LinkDetail;
import com.as.lingod.domain.LinkPool;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ban123
 * @since 2019-12-28
 */
public interface LinkPoolService extends IService<LinkPool> {

    /**
     * 获取去线别上一条数据
     *
     * @param name n
     * @return r
     */
    LinkPool getLastLink(String name);

    /**
     * 保存数据
     *
     * @param linkList l
     * @param linkPool l
     * @return r
     */
    boolean saveLinkInfo(List<LinkDetail> linkList, LinkPool linkPool);
}
