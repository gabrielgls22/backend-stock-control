package com.xts.stock.control.entrypoint.stock.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

    @NotBlank(message = "BarCode is required")
    private String barCode;

    @NotBlank(message = "Supplier name is required")
    private String supplierName;

    @NotBlank(message = "Material name is required")
    private String materialName;

    @NotBlank(message = "Batch is required")
    private String batch;

    @NotBlank(message = "Length is required")
    private String length;

    @NotBlank(message = "Width is required")
    private String width;
}
