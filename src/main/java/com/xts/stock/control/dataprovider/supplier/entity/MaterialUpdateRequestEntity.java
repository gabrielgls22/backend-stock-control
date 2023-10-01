package com.xts.stock.control.dataprovider.supplier.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialUpdateRequestEntity {

    private String cnpj;
    private String code;
    private String name;
    private String description;
}
