package com.xts.stock.control.dataprovider.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMaterialStockEntity {

    private String supplierName;
    private String materialName;
    private String width;
    private String length;
    private String barCode;
}
