package com.xts.stock.control.usecase.writeoff.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WriteOffMaterialsDomain {

    private String barCode;
    private String name;
    private String supplier;
    private String batch;
}
