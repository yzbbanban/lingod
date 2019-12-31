package com.as.lingod.dao;

import com.as.lingod.domain.FaLinkPool;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ban123
 * @since 2019-12-28
 */
public interface FaLinkPoolMapper extends BaseMapper<FaLinkPool> {

    /**
     * 获取上一条数据
     *
     * @param name 线别名
     * @return re
     */
    FaLinkPool getLastLink(String name);
}
