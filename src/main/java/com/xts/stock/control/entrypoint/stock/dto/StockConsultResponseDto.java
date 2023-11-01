package com.xts.stock.control.entrypoint.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockConsultResponseDto {

    private String supplierName;
    private String materialName;
    private String width;
    private String length;
}
