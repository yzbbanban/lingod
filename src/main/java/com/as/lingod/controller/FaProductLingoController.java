package com.as.lingod.controller;


import com.as.lingod.common.util.ResultJson;
import com.as.lingod.domain.FaProductLingo;
import com.as.lingod.domain.FaProductLingoCalc;
import com.as.lingod.service.FaProductLingoCalcService;
import com.as.lingod.service.FaProductLingoService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
    public ResultJson<String> saveLingoResult(String faCalcJson, String faLingoJson) {
        Gson gson = new Gson();
        FaProductLingoCalc faCalc = gson.fromJson(faCalcJson, new TypeToken<FaProductLingoCalc>() {
        }.getType());
        List<FaProductLingo> faLingoList = gson.fromJson(faLingoJson, new TypeToken<List<FaProductLingo>>() {
        }.getType());
        Map<String, Object> map = new HashMap<>(4);
        map.put("edition", faCalc.getEdition());
        map.put("protype", faCalc.getProtype());
        map.put("totalpeo", faCalc.getTotalpeo());
        map.put("nameId", faCalc.getNameId());
        List<FaProductLingoCalc> faLingo = faProductLingoCalcService.selectByMap(map);
        if (!CollectionUtils.isEmpty(faLingo)) {
            return ResultJson.createByErrorMsg("already exist");
        }

        boolean r = faProductLingoCalcService.insertAllColumn(faCalc);
        //先设置calc id
        Integer calcId = faCalc.getId();
        for (int i = 0, len = faLingoList.size(); i < len; i++) {
            FaProductLingo faProductLingo = faLingoList.get(i);
            faProductLingo.setCalcId(calcId);
        }
        boolean row = faProductLingoService.insertBatch(faLingoList);
        if (row && r) {
            return ResultJson.createBySuccess();
        }
        throw new RuntimeException("error");

    }

    @PostMapping("delAndSave")
    @Transactional(rollbackFor = Exception.class)
    public ResultJson<String> delAndSaveLingoResult(String faCalcJson, String faLingoJson) {
        Gson gson = new Gson();
        FaProductLingoCalc faCalc = gson.fromJson(faCalcJson, new TypeToken<FaProductLingoCalc>() {
        }.getType());
        List<FaProductLingo> faLingoList = gson.fromJson(faLingoJson, new TypeToken<List<FaProductLingo>>() {
        }.getType());
        Map<String, Object> map = new HashMap<>(4);
        map.put("edition", faCalc.getEdition());
        map.put("protype", faCalc.getProtype());
        map.put("totalpeo", faCalc.getTotalpeo());
        map.put("nameId", faCalc.getNameId());
        List<FaProductLingoCalc> faLingoCalc = faProductLingoCalcService.selectByMap(map);
        //删除原有记录
        Integer cId = faLingoCalc.get(0).getId();
        if (!faProductLingoCalcService.deleteById(cId)) {
            throw new RuntimeException("error");
        }
        //查询原有具体记录
        Map<String, Object> calc = new HashMap<>(4);
        calc.put("calcId", cId);
        List<FaProductLingo> faLingo = faProductLingoService.selectByMap(calc);
        for (int i = 0; i < faLingo.size(); i++) {
            if (!faProductLingoService.deleteById(faLingo.get(i).getId())) {
                throw new RuntimeException("error");
            }
        }

        boolean r = faProductLingoCalcService.insertAllColumn(faCalc);
        //先设置calc id
        Integer calcId = faCalc.getId();
        for (int i = 0, len = faLingoList.size(); i < len; i++) {
            FaProductLingo faProductLingo = faLingoList.get(i);
            faProductLingo.setCalcId(calcId);
        }
        boolean row = faProductLingoService.insertBatch(faLingoList);
        if (row && r) {
            return ResultJson.createBySuccess();
        }
        throw new RuntimeException("error");

    }

}

