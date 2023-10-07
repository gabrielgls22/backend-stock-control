package com.xts.stock.control.dataprovider.writeoff.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteWriteOffEntity {

    private String writeOffDate;
    private String writeOffCode;
}
