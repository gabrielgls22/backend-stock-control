package com.xts.stock.control.entrypoint.consumption.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionConsultRequestDto {

    @NotBlank(message = "supplierName is required")
    private String supplierName;

    @NotBlank(message = "supplierCnpj is required")
    private String supplierCnpj;

    @NotBlank(message = "firstDay is required")
    private String firstDay;

    @NotBlank(message = "lastDay is required")
    private String lastDay;
}
