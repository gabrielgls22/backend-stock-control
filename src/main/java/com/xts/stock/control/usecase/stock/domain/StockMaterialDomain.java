package com.xts.stock.control.usecase.stock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockMaterialDomain {

    private String materialName;
    private List<MaterialDetailsDomain> materialsDetails;
}
