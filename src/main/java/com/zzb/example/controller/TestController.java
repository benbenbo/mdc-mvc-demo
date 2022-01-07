package com.zzb.example.controller;

import com.google.common.collect.Lists;
import com.zzb.example.model.request.SortRequest;
import com.zzb.example.model.thread.MyForkJoinTask;
import com.zzb.example.service.SortService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

/**
 * @author XBird
 * @date 2021/12/28
 **/
@Slf4j
@Api(value="测试接口集合",tags="测试接口集合")
@RestController
public class TestController {

    @Autowired
    private SortService sortService;

    @ApiOperation(value="简单test接口",notes="\n开发者： 郑炳元")
    @PostMapping(value="/test")
    public String test(){
        log.info("访问test接口");
        return "test";
    }

    @ApiOperation(value="测试创建一个ForkJoinPool",notes="\n开发者： 郑炳元")
    @PostMapping(value="/testCreateForkJoinPool")
    public Integer testCreateForkJoinPool(){
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        // 提交可分解的PrintTask任务
        ForkJoinTask<Integer> taskFuture =  forkJoinPool.submit(new MyForkJoinTask(1,1001));
        Integer result = null;
        try {
            result = taskFuture.get();
            log.info("result = " + result);
        } catch (InterruptedException | ExecutionException e) {
            log.error("计算失败：",e);
        }
        return result;
    }

    @ApiOperation(value="获取当前CPU内核数",notes="\n开发者： 郑炳元")
    @PostMapping(value="/getAvailProcessors")
    public Integer getAvailProcessors(){
        return Runtime.getRuntime().availableProcessors();
    }

    @ApiOperation(value="归并排序",notes="\n开发者： 郑炳元")
    @PostMapping(value="/mergeSort")
    public void mergeSort(){
        sortService.testMergeSort();
    }

    @ApiOperation(value="归并排序(使用ForkJoin框架)",notes="\n开发者： 郑炳元")
    @PostMapping(value="/mergeSortWithForkJoin")
    public void mergeSortWithForkJoin(){
        sortService.testMergeSortWithForkJoin();
    }
}
