package com.xts.stock.control.usecase.writeoff.domain;

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
public class WriteOffSearchRequestDomain {

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

    private String firstDay;
    private String lastDay;
}
