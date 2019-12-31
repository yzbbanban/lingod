package com.as.lingod.service;

import com.as.lingod.domain.FaLinkDetail;
import com.as.lingod.domain.FaLinkPool;
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
public interface FaLinkPoolService extends IService<FaLinkPool> {

    /**
     * 获取去线别上一条数据
     *
     * @param name n
     * @return r
     */
    FaLinkPool getLastLink(String name);

    /**
     * 保存数据
     *
     * @param linkList l
     * @param linkPool l
     * @return r
     */
    boolean saveLinkInfo(List<FaLinkDetail> linkList, FaLinkPool linkPool);
}
