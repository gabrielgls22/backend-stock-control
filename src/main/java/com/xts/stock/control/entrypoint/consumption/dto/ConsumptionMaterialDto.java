package com.xts.stock.control.entrypoint.consumption.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionMaterialDto {

    private String supplier;
    private Integer lengthUsed;
    private Integer removedFromStock;
    private String width;
    private String length;
    private String widthAndLength;
}
