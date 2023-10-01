package com.xts.stock.control.entrypoint.supplier.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierUpdateRequestDto {

     @NotBlank(message = "Old cnpj is required")
     private String cnpj;

     @NotBlank(message = "New cnpj is required")
     private String newCnpj;

     @NotBlank(message = "New supplier is required")
     private String newSupplier;
}
