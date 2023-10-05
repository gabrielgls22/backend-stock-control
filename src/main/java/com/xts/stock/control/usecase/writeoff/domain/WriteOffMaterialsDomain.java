package com.xts.stock.control.usecase.writeoff.domain;

import jakarta.validation.constraints.NotBlank;
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
    private String description;
}
