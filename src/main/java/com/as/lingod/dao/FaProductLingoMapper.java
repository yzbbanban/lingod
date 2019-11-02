package com.as.lingod.dao;

import com.as.lingod.domain.FaProductLingo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ban
 * @since 2019-09-25
 */

public interface FaProductLingoMapper extends BaseMapper<FaProductLingo> {


    int updateCalc(@Param("id") int id, @Param("calcId") int calcId);

    List<Integer> getFaProId(@Param("id") int id, @Param("totalPeo") int totalPro);

}
