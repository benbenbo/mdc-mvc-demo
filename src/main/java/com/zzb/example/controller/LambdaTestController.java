package com.zzb.example.controller;

import com.google.common.collect.Lists;
import com.zzb.example.service.LambdaTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Lambda功能测试
 *
 * @author XBird
 * @date 2022/1/15
 **/
@RestController(value = "/lambda/test")
@AllArgsConstructor
@Api(value="Lambda功能测试",tags="Lambda功能测试")
public class LambdaTestController {
    private final LambdaTestService lambdaTestService;

    @ApiOperation(value="测试收集器Collectors.collectionAndThen方法",notes="\n开发者：郑炳元")
    @PostMapping(value="/testCollectingAndThen")
    public String testCollectingAndThen(){
        lambdaTestService.testCollectingAndThen();
        return "1";
    }
}
