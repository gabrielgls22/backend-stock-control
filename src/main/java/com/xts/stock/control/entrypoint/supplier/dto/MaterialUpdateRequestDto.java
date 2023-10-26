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
public class MaterialUpdateRequestDto {

    @NotBlank(message = "Supplier cnpj is required")
    private String cnpj;

    @NotBlank(message = "Material code is required")
    private String code;

    @NotBlank(message = "Material name is required")
    private String name;

    private String description;
}
