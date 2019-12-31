package com.as.lingod.dao;

import com.as.lingod.domain.FaSataWork;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ban123
 * @since 2019-12-29
 */
public interface FaSataWorkMapper extends BaseMapper<FaSataWork> {

    /**
     * 获取分组
     *
     * @return r
     */
    List<Integer> getDistinctCount();

}
