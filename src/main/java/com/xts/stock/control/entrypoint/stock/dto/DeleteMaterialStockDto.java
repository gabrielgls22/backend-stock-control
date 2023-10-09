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
public class DeleteMaterialStockDto {

    @NotBlank(message = "Supplier name is required")
    private String supplierName;

    @NotBlank(message = "Material name is required")
    private String materialName;

    @NotBlank(message = "BarCode is required")
    private String barCode;
}
