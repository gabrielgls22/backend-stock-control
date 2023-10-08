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
public class MaterialDetailsDto {

    private String batch;
    private String length;
    private String width;
    private Integer quantity;
    private List<String> barCodes;
}
