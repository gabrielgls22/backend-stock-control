package com.xts.stock.control.dataprovider.writeoff.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWriteOffRequestEntity {

    private String writeOffCode;
    private String writeOffDate;
    private String costumerName;
    private String costumerCnpj;
    private String tagName;
    private String tagCode;
    private String tagQuantity;
    private String serviceOrder;
    private String firstDay;
    private String lastDay;
}
