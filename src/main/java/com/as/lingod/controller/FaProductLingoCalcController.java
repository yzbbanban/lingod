package com.as.lingod.controller;


import com.as.lingod.common.util.ResultJson;
import com.as.lingod.domain.FaProductLingo;
import com.as.lingod.domain.FaProductLingoCalc;
import com.as.lingod.domain.dto.FaProductLingoDTO;
import com.as.lingod.domain.dto.LingoProDTO;
import com.as.lingod.domain.vo.FaProductLingoVO;
import com.as.lingod.service.FaProductLingoCalcService;
import com.as.lingod.service.FaProductLingoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ban
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/v1/proCalc")
public class FaProductLingoCalcController {

    @Autowired
    private FaProductLingoCalcService faProductLingoCalcService;

    @GetMapping("list")
    public ResultJson<List<FaProductLingoVO>> getList(LingoProDTO lingoProDTO) {
        //根据工序号查询人数
        List<FaProductLingoVO> list = faProductLingoCalcService.selectProList(lingoProDTO);

        return ResultJson.createBySuccess(list);
    }

    @PostMapping("add")
    public ResultJson<String> addCalc(FaProductLingoDTO dto) {

        try {
            if (faProductLingoCalcService.add(dto)) {
                return ResultJson.createBySuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultJson.createByError();
        }

        return ResultJson.createByError();
    }

}

