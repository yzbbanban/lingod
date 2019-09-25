package com.as.lingod.controller;


import com.as.lingod.common.util.ResultJson;
import com.as.lingod.domain.FaFlow;
import com.as.lingod.service.FaFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ban123
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/v1/faFlow")
public class FaFlowController {

    @Autowired
    private FaFlowService faFlowService;

    @GetMapping("list")
    public ResultJson<List<FaFlow>> getFaFlowList() {

        List<FaFlow> faFlowList = faFlowService.selectList(null);
        return ResultJson.createBySuccess(faFlowList);
    }
}

