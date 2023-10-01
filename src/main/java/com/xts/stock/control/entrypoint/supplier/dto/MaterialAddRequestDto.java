package com.xts.stock.control.entrypoint.supplier.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialAddRequestDto {

    @NotBlank(message = "Supplier cpnj is required")
    private String supplierCnpj;

    @NotBlank(message = "Material name is required")
    private String name;

    @NotBlank(message = "Material description is required")
    private String description;
}