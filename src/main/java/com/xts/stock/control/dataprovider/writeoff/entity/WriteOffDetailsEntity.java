package com.xts.stock.control.dataprovider.writeoff.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WriteOffDetailsEntity {

    private String writeOffDate;
    private String writeOffCode;
    private String costumerCnpj;
    private String costumerName;
    private String tagCode;
    private String tagName;
    private String serviceOrder;
    private List<WriteOffMaterialEntity> materials;

}
