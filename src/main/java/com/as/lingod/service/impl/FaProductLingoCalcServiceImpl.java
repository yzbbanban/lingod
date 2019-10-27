package com.as.lingod.service.impl;

import com.as.lingod.common.util.ResultJson;
import com.as.lingod.dao.FaProductLingoMapper;
import com.as.lingod.domain.FaProductLingo;
import com.as.lingod.domain.FaProductLingoCalc;
import com.as.lingod.dao.FaProductLingoCalcMapper;
import com.as.lingod.domain.dto.FaProductLingoDTO;
import com.as.lingod.domain.dto.LingoProDTO;
import com.as.lingod.domain.vo.FaProductLingoVO;
import com.as.lingod.service.FaProductLingoCalcService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ban
 * @since 2019-09-25
 */
@Service
public class FaProductLingoCalcServiceImpl extends ServiceImpl<FaProductLingoCalcMapper, FaProductLingoCalc> implements FaProductLingoCalcService {

    @Autowired
    private FaProductLingoCalcMapper faProductLingoCalcMapper;

    @Autowired
    private FaProductLingoMapper faProductLingoMapper;

    @Override
    public List<FaProductLingoVO> selectProList(LingoProDTO lingoProDTO) {

        return faProductLingoCalcMapper.selectProList(lingoProDTO);
    }

}
