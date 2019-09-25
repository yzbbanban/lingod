package com.as.lingod.controller;


import com.as.lingod.common.util.ResultJson;
import com.as.lingod.domain.FaProduct;
import com.as.lingod.service.FaProductService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RestController("/v1/faProduct")
public class FaProductController {

    @Autowired
    private FaProductService faProductService;

    @GetMapping()
    public ResultJson<List<FaProduct>> getList() {
        Wrapper<FaProduct> wrapper=new EntityWrapper<>();
        List<FaProduct> list = faProductService.selectList(wrapper);
        return ResultJson.createBySuccess(list);
    }

}

