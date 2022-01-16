package com.zzb.example.service;

import com.google.common.collect.Lists;
import com.zzb.example.model.dto.SaleTypeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
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

        List<SaleTypeDto> saleTypeDtoList = getSaleTypeDtoList();
        Map<String, Integer> minSaleTypeIdMap = saleTypeDtoList.stream().collect(Collectors.groupingBy(o -> o.getYsbProviderId() + "-" + o.getBatchNo(),
                Collectors.collectingAndThen(Collectors.minBy(Comparator.comparing(SaleTypeDto::getId)),
                        o -> o.orElseGet(SaleTypeDto::new).getId())));
        for (Map.Entry<String, Integer> entry : minSaleTypeIdMap.entrySet()) {
            log.info("子公司-批次：{}的最小id是{}",entry.getKey(),entry.getValue());
        }
    }

    private Integer summarize(List<Integer> list){
        Integer collect = list.stream().collect(Collectors.summingInt(o -> o));
        return collect;
    }

    public List<SaleTypeDto> getSaleTypeDtoList(){
        SaleTypeDto a = new SaleTypeDto(1, 781, "A");
        SaleTypeDto a2 = new SaleTypeDto(2, 781, "A");
        SaleTypeDto a3 = new SaleTypeDto(3, 498, "B");
        SaleTypeDto a4 = new SaleTypeDto(4, 498, "B");
        SaleTypeDto a5 = new SaleTypeDto(5, 1520, "C");
        SaleTypeDto a6 = new SaleTypeDto(6, 1520, "C");
        List<SaleTypeDto> list = Lists.newArrayList();
        list.add(a);
        list.add(a2);
        list.add(a3);
        list.add(a4);
        list.add(a5);
        list.add(a6);
        return list;
    }
}
