package com.xts.stock.control.entrypoint.writeoff.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWriteOffRequestDto {

    @NotBlank(message = "writeOffCode is required")
    private String writeOffCode;

    @NotBlank(message = "writeOffDate is required")
    private String writeOffDate;

    @NotBlank(message = "customerName is required")
    private String costumerName;

    @NotBlank(message = "customerCnpj is required")
    private String costumerCnpj;

    @NotBlank(message = "tagName is required")
    private String tagName;

    @NotBlank(message = "tagCode is required")
    private String tagCode;

    @NotBlank(message = "tagQuantity is required")
    private String tagQuantity;

    @NotBlank(message = "serviceOrder is required")
    private String serviceOrder;

    private String searchCostumerCnpj;
    private String searchTagCode;
    private String searchMaterial;
    private String searchServiceOrder;
    private String firstDay;
    private String lastDay;
}
