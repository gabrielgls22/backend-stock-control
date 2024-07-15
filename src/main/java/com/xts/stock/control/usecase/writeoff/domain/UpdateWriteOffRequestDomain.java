package com.xts.stock.control.usecase.writeoff.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWriteOffRequestDomain {

    private String writeOffCode;
    private String writeOffDate;
    private String costumerName;
    private String costumerCnpj;
    private String tagName;
    private String tagCode;
    private String tagQuantity;
    private String serviceOrder;

    private String searchCostumerCnpj;
    private String searchTagCode;
    private String searchMaterial;
    private String searchServiceOrder;
    private String firstDay;
    private String lastDay;
}
