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
public class WriteOffDayMaterialsDto {

    @NotBlank(message = "Material name is required")
    private String name;

    @NotBlank(message = "Material supplier is required")
    private String supplier;

    @NotBlank(message = "Material batch is required")
    private String batch;
}
