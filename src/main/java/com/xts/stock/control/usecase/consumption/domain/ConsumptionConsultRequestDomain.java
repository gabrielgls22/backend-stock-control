package com.xts.stock.control.usecase.consumption.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionConsultRequestDomain {

    private String materialCode;
    private String firstDay;
    private String lastDay;
}
