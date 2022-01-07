package com.zzb.example.model.thread;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @author zhengbingyuan
 * @date 2022/1/4
 */

public class MyForkJoinTask extends RecursiveTask<Integer> {
    private final Logger log = LoggerFactory.getLogger(MyForkJoinTask.class);
    // 每个“小任务”只最多只打印50个数
    private static final int THRESHOLD = 200;
    private int startValue;
    private int endValue;

    // 打印从start到end的任务
    public MyForkJoinTask(int start, int end){
        this.startValue = start;
        this.endValue = end;
    }
    @Override
    protected Integer compute() {
        // 当end与start之间的差小于THRESHOLD时，开始打印
        if(endValue - startValue < THRESHOLD){
            log.info("开始计算的部分：startValue = " + startValue + ";endValue = " + endValue);
            Integer totalValue = 0;
            for(int index = this.startValue ; index <= this.endValue  ; index++) {
                totalValue += index;
            }
            return totalValue;
        }else{
            // 如果当end与start之间的差大于THRESHOLD时，即要打印的数超过50个
            // 将大任务分解成两个小任务。
            int middle = (startValue + endValue) / 2;
            MyForkJoinTask left = new MyForkJoinTask(startValue, middle);
            MyForkJoinTask right = new MyForkJoinTask(middle, endValue);
            // 并行执行两个“小任务”
            left.fork();
            right.fork();
            return left.join()+right.join();
        }
    }
}
