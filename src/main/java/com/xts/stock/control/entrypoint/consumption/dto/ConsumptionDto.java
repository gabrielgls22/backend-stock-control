package com.xts.stock.control.entrypoint.consumption.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionDto {

    private String supplier;
    private List<ConsumptionMaterialDto> materials;
}
