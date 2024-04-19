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

    private String costumerCnpj;
    private String costumerName;
    private String tagCode;
    private String tagName;
    private String tagQuantity;
    private String serviceOrder;
    private List<WriteOffMaterialsDto> materials;
}
