package com.xts.stock.control.dataprovider.costumer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagUpdateRequestEntity {

    private String cnpj;
    private String code;
    private String name;
}
