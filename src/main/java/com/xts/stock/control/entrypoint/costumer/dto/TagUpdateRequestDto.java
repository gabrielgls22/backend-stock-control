package com.xts.stock.control.entrypoint.costumer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagUpdateRequestDto {

    @NotBlank(message = "Costumer cnpj is required")
    private String cnpj;

    @NotBlank(message = "Tag code is required")
    private String code;

    @NotBlank(message = "Tag name is required")
    private String name;
}
