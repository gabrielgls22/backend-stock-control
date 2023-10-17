package com.xts.stock.control.entrypoint.stock.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

    private List<@Valid @NotBlank String> barCodes;

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
