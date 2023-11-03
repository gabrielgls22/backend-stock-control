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

    @NotBlank(message = "supplierName name is required")
    private String supplierName;

    @NotBlank(message = "materialName is required")
    private String materialName;

    @NotBlank(message = "width is required")
    private String width;

    @NotBlank(message = "length is required")
    private String length;

    @NotBlank(message = "barCode is required")
    private String barCode;
}
