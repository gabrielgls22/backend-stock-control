package com.xts.stock.control.entrypoint.costumer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagAddRequestDto {

    @NotBlank(message = "Costumer cpnj is required")
    private String costumerCnpj;

    @NotBlank(message = "Tag name is required")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;
}
