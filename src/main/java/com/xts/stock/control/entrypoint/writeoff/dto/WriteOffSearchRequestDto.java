package com.xts.stock.control.entrypoint.writeoff.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class WriteOffSearchRequestDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String costumerCnpj;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String costumerName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tagCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tagName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String material;

    @NotBlank(message = "firstDay search is required")
    private String firstDay;

    @NotBlank(message = "lastDay search is required")
    private String lastDay;
}
