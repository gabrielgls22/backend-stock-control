package com.xts.stock.control.usecase.costumer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostumerUpdateRequestDomain {

     private String cnpj;
     private String newCnpj;
     private String newCostumer;
}
