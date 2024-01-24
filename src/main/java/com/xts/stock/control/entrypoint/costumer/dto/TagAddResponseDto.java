package com.xts.stock.control.entrypoint.costumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagAddResponseDto {

    private String costumerCnpj;
    private String name;
    private String code;
}
