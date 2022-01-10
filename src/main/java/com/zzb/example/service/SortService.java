package com.zzb.example.service;

import com.google.common.collect.Lists;
import com.zzb.example.model.thread.MergeSortTask;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * 排序算法集合
 *
 * @author zhengbingyuan
 * @date 2022/1/6
 */
@Service
public class SortService {
    private static final Logger log = LoggerFactory.getLogger(SortService.class);
    private static int MAX = 1000000;
    public int[] getRandomData(){
        Random random = new Random();
        int[] data = new int[MAX];
        for (int i = 0; i < MAX; i++) {
            int i1 = random.nextInt(10000000);
            data[i] = i1;
        }
        return data;
    }

    public void testMergeSort() {
        int[] data = getRandomData();
        long startMill = System.currentTimeMillis();
        mergeSort(data);
        long endMill = System.currentTimeMillis();
        long time = endMill - startMill;
        log.info("归并排序（100000个数）花费时间："+time+"毫秒");
    }

    public int[] mergeSort(int[] list){
        if(Objects.isNull(list)||list.length==0){
            return list;
        }
        return mergeSort2(list);
    }

    public int[] mergeSort2(int[] list){
        if(Objects.isNull(list)||list.length==0){
            return list;
        }
        if(list.length==1){
            return list;
        }
        Integer min = list.length/2;
        int[] listA = Arrays.copyOfRange(list, 0, min);
        int[] listB = Arrays.copyOfRange(list,min,list.length);
        int[]  sortListA = mergeSort2(listA);
        int[]  sortListB = mergeSort2(listB);
        int[]  newList = new int[list.length];
        for (int z=0,i=0,j=0;i<sortListA.length||j<sortListB.length;){
            if(i>=sortListA.length){
                newList[z++]=sortListB[j++];
            }else if(j>=sortListB.length){
                newList[z++]=sortListA[i++];
            }else if(sortListA[i]>sortListB[j]){
                newList[z++]=sortListB[j++];
            }else{
                newList[z++]=sortListA[i++];
            }
        }
        return newList;
    }

    public void testMergeSortWithForkJoin(){
        int[] data = getRandomData();
        long startMill = System.currentTimeMillis();
        mergeSortWithForkJoin(data);
        long endMill = System.currentTimeMillis();
        long time = endMill - startMill;
        log.info("归并排序ForkJoin（100000个数）花费时间："+time+"毫秒");
    }

    public int[] mergeSortWithForkJoin(int[] list){
        if(Objects.isNull(list)||list.length==0){
            return list;
        }
        ForkJoinPool pool = new ForkJoinPool(4);
        ForkJoinTask<int[]> submit = pool.submit(new MergeSortTask(list));
        return submit.join();
    }


}
