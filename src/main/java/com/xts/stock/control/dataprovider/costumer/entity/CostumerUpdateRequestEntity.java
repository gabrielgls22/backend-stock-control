package com.xts.stock.control.dataprovider.costumer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostumerUpdateRequestEntity {

     private String cnpj;
     private String newCnpj;
     private String newCostumer;
}
