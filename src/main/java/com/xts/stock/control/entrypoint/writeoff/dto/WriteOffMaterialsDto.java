package com.xts.stock.control.entrypoint.writeoff.dto;

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
public class WriteOffMaterialsDto {

    private String barCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lengthUsed;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String supplier;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String batch;
}
