package com.xts.stock.control.usecase.supplier.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialAddRequestDomain {

    private String supplierCnpj;
    private String code;
    private String name;
    private String description;
}
