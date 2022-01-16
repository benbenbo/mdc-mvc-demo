package com.zzb.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 仿销售订单DTO测试Lambda功能
 *
 * @author XBird
 * @date 2022/1/16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleTypeDto {
    private Integer id;
    private Integer ysbProviderId;
    private String batchNo;
}
