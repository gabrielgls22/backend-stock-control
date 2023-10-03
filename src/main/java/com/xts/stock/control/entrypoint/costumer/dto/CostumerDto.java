package com.xts.stock.control.entrypoint.costumer.dto;

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
public class CostumerDto {

    @NotBlank(message = "Supplier name is required")
    private String costumerName;

    @NotBlank(message = "Supplier cnpj is required")
    private String costumerCnpj;

    @NotNull(message = "Material list is required")
    private List<TagDto> tagList;
}
