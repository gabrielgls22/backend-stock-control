package com.xts.stock.control.dataprovider.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDetailsEntity {

    private String length;
    private String width;
    private Integer quantity;
    private List<BatchDetailsEntity> batchDetails;
}
