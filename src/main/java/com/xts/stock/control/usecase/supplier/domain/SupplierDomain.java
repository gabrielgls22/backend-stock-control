package com.xts.stock.control.usecase.supplier.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDomain {

    private String supplierName;
    private String supplierCnpj;
    private List<MaterialDomain> materialList;
}
