package com.as.lingod.controller;


import com.as.lingod.common.util.ResultJson;
import com.as.lingod.service.FaProductLingoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("save")
    public ResultJson<String> saveLingoResult() {
        faProductLingoService.selectList(null);
        return ResultJson.createBySuccess();
    }


}

