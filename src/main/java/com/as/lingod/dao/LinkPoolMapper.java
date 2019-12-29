package com.as.lingod.dao;

import com.as.lingod.domain.LinkPool;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ban123
 * @since 2019-12-28
 */
public interface LinkPoolMapper extends BaseMapper<LinkPool> {

    /**
     * 获取上一条数据
     *
     * @param name 线别名
     * @return re
     */
    LinkPool getLastLink(String name);
}
