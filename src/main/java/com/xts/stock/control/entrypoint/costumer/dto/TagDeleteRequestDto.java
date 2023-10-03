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
public class TagDeleteRequestDto {

    @NotBlank(message = "Costumer cnpj is required")
    private String costumerCnpj;

    @NotBlank(message = "Tag code is required")
    private String tagCode;
}
