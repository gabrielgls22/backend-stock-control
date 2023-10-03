package com.xts.stock.control.dataprovider.costumer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagAddRequestEntity {

    private String costumerCnpj;
    private String code;
    private String name;
}
