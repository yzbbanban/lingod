package com.as.lingod.controller;


import com.as.lingod.common.util.ResultJson;
import com.as.lingod.domain.FaProductLingo;
import com.as.lingod.domain.FaProductLingoCalc;
import com.as.lingod.domain.dto.FaProductLingoDTO;
import com.as.lingod.service.FaProductLingoCalcService;
import com.as.lingod.service.FaProductLingoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<String, Object> map = new HashMap<>(4);
        FaProductLingoCalc faCalc = dto.getFaProductLingoCalc();
        map.put("edition", faCalc.getEdition());
        map.put("protype", faCalc.getProtype());
        map.put("totalpeo", faCalc.getTotalpeo());
        map.put("nameId", faCalc.getNameId());
        List<FaProductLingo> faLingo = faProductLingoService.selectByMap(map);
        if (!CollectionUtils.isEmpty(faLingo)) {
            return ResultJson.createByErrorMsg("already exist");
        }

        boolean row = faProductLingoService.insertBatch(dto.getFaProductLingo());
        boolean r = faProductLingoCalcService.insertAllColumn(faCalc);
        if (row && r) {
            throw new RuntimeException("error");
        }

        return ResultJson.createBySuccess();
    }


}

