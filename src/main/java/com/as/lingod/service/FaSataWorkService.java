package com.as.lingod.service;

import com.as.lingod.domain.FaSataWork;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ban123
 * @since 2019-12-29
 */
public interface FaSataWorkService extends IService<FaSataWork> {

    /**
     * 获取分组
     *
     * @return r
     */
    List<Integer> getDistinctCount();

    /**
     * s
     *
     * @param faMap f
     */
    void saveData(Map<String, List<FaSataWork>> faMap);
}
