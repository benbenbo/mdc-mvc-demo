package com.zzb.example.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhengbingyuan
 * @date 2022/1/6
 */
@Data
@ApiModel(value="排序数据请求")
public class SortRequest {
    @ApiModelProperty(value="排序列表",example = "[2,3,4,1]",position = 1)
    private List<Integer> dataList;
}
