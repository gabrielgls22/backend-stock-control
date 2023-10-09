package com.xts.stock.control.entrypoint.stock;

import com.xts.stock.control.entrypoint.stock.dto.DeleteMaterialStockDto;
import com.xts.stock.control.entrypoint.stock.dto.StockDto;
import com.xts.stock.control.entrypoint.stock.dto.StockResponseDto;
import com.xts.stock.control.entrypoint.stock.mapper.StockEntrypointMapper;
import com.xts.stock.control.usecase.stock.DeleteMaterialStockUseCase;
import com.xts.stock.control.usecase.stock.GetAllStockUseCase;
import com.xts.stock.control.usecase.stock.StockRegisterUseCase;
import com.xts.stock.control.usecase.stock.domain.DeleteMaterialStockDomain;
import com.xts.stock.control.usecase.stock.domain.StockDomain;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {

    private final StockEntrypointMapper stockEntrypointMapper;

    private final StockRegisterUseCase stockRegisterUseCase;

    private final GetAllStockUseCase getAllStockUseCase;

    private final DeleteMaterialStockUseCase deleteMaterialStockUseCase;

    @PostMapping("/register")
    @Operation(summary = "Register stock",
            description = "Should register stock",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private void registerNewStock(@RequestBody @Valid final StockDto requestDto) {

        final StockDomain requestDomain =
                stockEntrypointMapper.registerStockRequestDtoToDomain(requestDto);

        stockRegisterUseCase.execute(requestDomain);
    }

    @GetMapping
    @Operation(summary = "Get all stock",
            description = "Should get all stock",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private @ResponseBody List<StockResponseDto> getAllStock() {

        final List<StockDomain> responseDomain = getAllStockUseCase.execute();

        return stockEntrypointMapper.getAllStockDomainToEntity(responseDomain);
    }

    @PostMapping("/delete")
    @Operation(summary = "Delete material from stock",
            description = "Should delete material from stock",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private void deleteMaterialStock(@RequestBody @Valid final DeleteMaterialStockDto requestDto) {

        final DeleteMaterialStockDomain requestDomain =
                stockEntrypointMapper.deleteMaterialStockDtoToDomain(requestDto);

        deleteMaterialStockUseCase.execute(requestDomain);
    }
}
