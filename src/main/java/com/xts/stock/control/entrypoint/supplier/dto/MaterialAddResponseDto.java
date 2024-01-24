package com.xts.stock.control.entrypoint.supplier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialAddResponseDto {

    private String supplierCnpj;
    private String name;
    private String code;
}
