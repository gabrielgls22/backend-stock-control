package com.xts.stock.control.entrypoint.costumer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostumerUpdateRequestDto {

     @NotBlank(message = "Old cnpj is required")
     private String cnpj;

     @NotBlank(message = "New cnpj is required")
     private String newCnpj;

     @NotBlank(message = "New costumer is required")
     private String newCostumer;
}
