package com.xts.stock.control.entrypoint.writeoff.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
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
public class WriteOffSearchResponseDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String writeOffDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String writeOffCode;

    @NotBlank(message = "Costumer cnpj is required")
    private String costumerCnpj;

    @NotBlank(message = "Costumer name is required")
    private String costumerName;

    @NotBlank(message = "Tag code is required")
    private String tagCode;

    @NotBlank(message = "Tag name is required")
    private String tagName;

    @NotBlank(message = "serviceOrder are required")
    private String serviceOrder;

    @NotNull(message = "Material information")
    private List<@Valid WriteOffMaterialsDto> materials;
}
