package com.zzb.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Lambda功能测试
 *
 * @author XBird
 * @date 2022/1/15
 **/
@Service
@Slf4j
public class LambdaTestService {
    public void testCollectingAndThen() {
        /**
         * collectingAndThen方法：map and reduce的方法
         * 总体来说，先用第一个参数接收的收集器归纳操作处理，然后把生成的集合/值传递给第二个参数传入的函数进行处理
         *
         * 第一个参数Collector<T,A,R> downstream：收集器，使用Collectors类的方法生成
         *
         * 第二个参数Function<T,R>，：finish操作，可自定义方法，也可以传递Collectors的方法,本质上是一个Function<T,R>，
         * T就是第一个参数返回的类型，R就是finish操作返回的类型
         */
        List<Integer> list = Stream.of(1, 2, 3, 4, 5, 6).collect(Collectors.toList());
        Integer i = list.stream().collect(Collectors.collectingAndThen(Collectors.toList(), this::summarize));
        log.info("规约期值：{}", i);
    }

    private Integer summarize(List<Integer> list){
        Integer collect = list.stream().collect(Collectors.summingInt(o -> o));
        return collect;
    }
}
