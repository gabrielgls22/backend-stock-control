package com.xts.stock.control.usecase.writeoff.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WriteOffDomain {

    private String writeOffDate;
    private String writeOffCode;
    private String costumerCnpj;
    private String costumerName;
    private String tagCode;
    private String tagName;
    private String serviceOrder;
    private List<WriteOffMaterialsDomain> materials;
}
