package com.xts.stock.control.usecase.writeoff.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteWriteOffDomain {

    private String writeOffDate;
    private String writeOffCode;
}
