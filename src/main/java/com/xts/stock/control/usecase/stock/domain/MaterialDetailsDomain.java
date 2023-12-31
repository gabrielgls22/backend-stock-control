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
public class MaterialDetailsDomain {

    private String length;
    private String width;
    private Integer quantity;
    private List<BatchDetailsDomain> batchDetails;
}
