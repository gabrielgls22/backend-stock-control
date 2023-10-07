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
public class DeleteWriteOffDto {

    @NotBlank(message = "Write off date is required")
    private String writeOffDate;

    @NotBlank(message = "Write off code is required")
    private String writeOffCode;
}
