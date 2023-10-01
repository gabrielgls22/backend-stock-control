package com.xts.stock.control.entrypoint.supplier.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {

    @NotBlank(message = "Supplier name is required")
    private String supplierName;

    @NotBlank(message = "Supplier cnpj is required")
    private String supplierCnpj;

    @NotNull(message = "Material list is required")
    private List<MaterialDto> materialList;
}
