package com.zzb.example.model.thread;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.RecursiveTask;

/**
 * 归并排序任务
 *
 * @author zhengbingyuan
 * @date 2022/1/6
 */
public class MergeSortTask extends RecursiveTask<int[]> {
    private int[] dataList;

    public MergeSortTask(int[] dataList) {
        this.dataList = dataList;
    }

    @Override
    protected int[] compute() {
        if(Objects.isNull(dataList)||dataList.length==0){
            return dataList;
        }
        if(dataList.length==1){
            return dataList;
        }
        if(dataList.length==2){
            if(dataList[0]<=dataList[1]){
                return dataList;
            }else{
                int temp = dataList[0];
                dataList[0]=dataList[1];
                dataList[1]=temp;
                return dataList;
            }
        }
        Integer min = dataList.length/2;
        int[] listA = Arrays.copyOfRange(dataList, 0, min);
        int[] listB = Arrays.copyOfRange(dataList,min,dataList.length);
        MergeSortTask mergeSortTask = new MergeSortTask(listA);
        mergeSortTask.fork();
        MergeSortTask mergeSortTask1 = new MergeSortTask(listB);
        mergeSortTask1.fork();
        int[] sortListA = mergeSortTask.join();
        int[] sortListB = mergeSortTask1.join();
        int[]  newList = new int[dataList.length];
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
}
