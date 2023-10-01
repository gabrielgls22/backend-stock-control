package com.xts.stock.control.dataprovider.supplier.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDeleteRequestEntity {

    private String supplierCnpj;
    private String materialCode;
}
