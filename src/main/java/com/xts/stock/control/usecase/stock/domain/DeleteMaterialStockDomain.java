package com.xts.stock.control.usecase.stock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMaterialStockDomain {

    private String supplierName;
    private String materialName;
    private String width;
    private String length;
    private String barCode;
}
