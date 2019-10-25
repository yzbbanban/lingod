package com.as.lingod.service;

import com.as.lingod.domain.FaProductLingo;
import com.as.lingod.domain.FaProductLingoCalc;
import com.as.lingod.domain.dto.LingoProDTO;
import com.as.lingod.domain.vo.FaProductLingoVO;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ban
 * @since 2019-09-25
 */
public interface FaProductLingoCalcService extends IService<FaProductLingoCalc> {

    /**
     * 获取结果
     *
     * @param lingoProDTO 结果
     * @return 结果
     */
    List<FaProductLingoVO> selectProList(LingoProDTO lingoProDTO);


    /**
     * add
     *
     * @param faProductLingoCalc fac
     * @param faProductLingo     fa
     * @return re
     */
    boolean add(FaProductLingoCalc faProductLingoCalc, FaProductLingo faProductLingo);
}
