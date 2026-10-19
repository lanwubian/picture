package com.picture.controller;

import com.picture.common.BaseResponse;
import com.picture.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Health {

    @GetMapping("/health")
    public BaseResponse<String> healeth(){
        return ResultUtils.success("ok");
    }
}
