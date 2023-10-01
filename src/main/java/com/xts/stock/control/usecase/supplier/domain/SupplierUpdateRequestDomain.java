package com.xts.stock.control.usecase.supplier.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierUpdateRequestDomain {

     private String cnpj;
     private String newCnpj;
     private String newSupplier;
}
