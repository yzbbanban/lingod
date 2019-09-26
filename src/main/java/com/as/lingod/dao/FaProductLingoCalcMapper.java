package com.as.lingod.dao;

import com.as.lingod.domain.FaProductLingoCalc;
import com.as.lingod.domain.dto.LingoProDTO;
import com.as.lingod.domain.vo.FaProductLingoVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ban
 * @since 2019-09-25
 */
public interface FaProductLingoCalcMapper extends BaseMapper<FaProductLingoCalc> {

    /**
     * 获取 list
     *
     * @param lingoProDTO lingoProDTO
     * @return list
     */
    List<FaProductLingoVO> selectProList(LingoProDTO lingoProDTO);
}
