package com.xts.stock.control.usecase.consumption.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionMaterialDomain {

    private String name;
    private Integer lengthUsed;
    private Integer removedFromStock;
    private String width;
    private String length;
}
