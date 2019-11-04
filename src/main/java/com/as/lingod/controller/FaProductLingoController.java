package com.as.lingod.controller;


import com.as.lingod.common.util.ResultJson;
import com.as.lingod.domain.dto.FaProductLingoDTO;
import com.as.lingod.service.FaProductLingoCalcService;
import com.as.lingod.service.FaProductLingoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ban
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/v1/faProductLingo")
public class FaProductLingoController {


    @Autowired
    private FaProductLingoService faProductLingoService;
    @Autowired
    private FaProductLingoCalcService faProductLingoCalcService;

    @PostMapping("save")
    @Transactional(rollbackFor = Exception.class)
    public ResultJson<String> saveLingoResult(FaProductLingoDTO dto) {
        boolean row = faProductLingoService.insertBatch(dto.getFaProductLingo());
        boolean r = faProductLingoCalcService.insertAllColumn(dto.getFaProductLingoCalc());
        if (row && r) {
            throw new RuntimeException("error");
        }

        return ResultJson.createBySuccess();
    }


}

