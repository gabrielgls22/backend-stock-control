package com.xts.stock.control.dataprovider.writeoff.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WriteOffMaterialEntity {

    private String barCode;
    private String lengthUsed;
    private String name;
    private String supplier;
    private String batch;
}
