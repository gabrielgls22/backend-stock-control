package com.xts.stock.control.dataprovider.supplier.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialEntity {

    private String code;
    private String name;
    private String description;
}
