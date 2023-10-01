package com.xts.stock.control.usecase.supplier.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialUpdateRequestDomain {

    private String cnpj;
    private String code;
    private String name;
    private String description;
}
