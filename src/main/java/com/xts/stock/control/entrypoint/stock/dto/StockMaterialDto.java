package com.xts.stock.control.entrypoint.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockMaterialDto {

    private String materialName;
    private List<MaterialDetailsDto> materialsDetails;
}
