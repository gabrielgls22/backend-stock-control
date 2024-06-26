package com.xts.stock.control.entrypoint.writeoff.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WriteOffDto {

    @NotBlank(message = "Costumer cnpj is required")
    private String costumerCnpj;

    @NotBlank(message = "Costumer name is required")
    private String costumerName;

    @NotBlank(message = "Tag code is required")
    private String tagCode;

    @NotBlank(message = "Tag name is required")
    private String tagName;

    @NotBlank(message = "Tag quantity is required")
    private String tagQuantity;

    @NotBlank(message = "Write off serviceOrder is required")
    private String serviceOrder;

    private List<@Valid WriteOffMaterialsDto> materials;
}
