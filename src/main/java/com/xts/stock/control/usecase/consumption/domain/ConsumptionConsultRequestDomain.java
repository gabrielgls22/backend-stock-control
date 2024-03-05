package com.xts.stock.control.usecase.consumption.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionConsultRequestDomain {

    private String supplierName;
    private String supplierCnpj;
    private String firstDay;
    private String lastDay;
}
