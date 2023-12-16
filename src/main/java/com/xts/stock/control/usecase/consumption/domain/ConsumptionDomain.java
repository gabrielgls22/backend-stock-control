package com.xts.stock.control.usecase.consumption.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionDomain {

    private String supplier;
    private List<ConsumptionMaterialDomain> materials;
}
