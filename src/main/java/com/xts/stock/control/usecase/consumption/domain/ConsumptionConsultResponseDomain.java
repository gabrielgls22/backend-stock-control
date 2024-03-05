package com.xts.stock.control.usecase.consumption.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionConsultResponseDomain {

    private String materialName;
    private String width;
    private String length;
    private Integer lengthUsed;
    private Integer removedFromStock;
}
