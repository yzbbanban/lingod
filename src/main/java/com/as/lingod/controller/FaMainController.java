package com.as.lingod.controller;


import com.as.lingod.common.util.ResultJson;
import com.as.lingod.domain.FaMain;
import com.as.lingod.service.FaMainService;
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
@RequestMapping("/faMain")
public class FaMainController {

    @Autowired
    private FaMainService faMainService;


    @GetMapping("list")
    public ResultJson<List<FaMain>> getFaMainList() {

        List<FaMain> faMainList = faMainService.selectList(null);
        return ResultJson.createBySuccess(faMainList);
    }
}

