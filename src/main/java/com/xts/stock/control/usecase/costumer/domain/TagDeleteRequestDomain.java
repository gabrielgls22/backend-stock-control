package com.xts.stock.control.usecase.costumer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagDeleteRequestDomain {

    private String costumerCnpj;
    private String tagCode;
}
