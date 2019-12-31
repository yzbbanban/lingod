package com.as.lingod.service.impl;

import com.as.lingod.domain.FaSataWork;
import com.as.lingod.dao.FaSataWorkMapper;
import com.as.lingod.service.FaSataWorkService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ban123
 * @since 2019-12-29
 */
@Service
public class FaSataWorkServiceImpl extends ServiceImpl<FaSataWorkMapper, FaSataWork> implements FaSataWorkService {

    @Autowired
    private FaSataWorkMapper faSataWorkMapper;

    /**
     * 获取分组
     *
     * @return r
     */
    @Override
    public List<Integer> getDistinctCount() {
        return faSataWorkMapper.getDistinctCount();
    }

    @Override
    public List<FaSataWork> selectEarlyList() {
        return null;
    }
}
