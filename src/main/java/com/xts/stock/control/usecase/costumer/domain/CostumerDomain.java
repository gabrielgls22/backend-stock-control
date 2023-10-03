package com.xts.stock.control.usecase.costumer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostumerDomain {

    private String costumerName;
    private String costumerCnpj;
    private List<TagDomain> tagList;
}
