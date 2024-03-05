package com.xts.stock.control.entrypoint.consumption.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionConsultResponseDto {

    private String materialName;
    private String widthAndLength;
    private Integer lengthUsed;
    private Integer removedFromStock;
}
