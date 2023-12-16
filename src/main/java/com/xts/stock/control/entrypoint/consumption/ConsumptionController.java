package com.xts.stock.control.entrypoint.consumption;

import com.xts.stock.control.entrypoint.consumption.dto.ConsumptionDto;
import com.xts.stock.control.entrypoint.consumption.mapper.ConsumptionEntrypointMapper;
import com.xts.stock.control.usecase.consumption.GetConsumptionUseCase;
import com.xts.stock.control.usecase.consumption.domain.ConsumptionDomain;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/consumption")
public class ConsumptionController {

    private final GetConsumptionUseCase getConsumptionUseCase;

    private final ConsumptionEntrypointMapper consumptionEntrypointMapper;

    @GetMapping
    @Operation(summary = "Get all consumption",
            description = "Should get all consumptions",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    public @ResponseBody @NotNull List<ConsumptionDto> getConsumption(
            @RequestParam("firstDay") @NotBlank final String firstDay,
            @RequestParam("lastDay") @NotBlank final String lastDay) {

        final List<ConsumptionDomain> responseDomain = getConsumptionUseCase.execute(firstDay, lastDay);

        return consumptionEntrypointMapper.getConsumptionDomainToDto(responseDomain);
    }
}
