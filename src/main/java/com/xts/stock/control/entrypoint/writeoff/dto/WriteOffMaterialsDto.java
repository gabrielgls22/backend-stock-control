package com.xts.stock.control.entrypoint.writeoff.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WriteOffMaterialsDto {

    @NotBlank(message = "Barcode is required")
    private String barCode;

    @NotBlank(message = "Description is required")
    private String description;
}
