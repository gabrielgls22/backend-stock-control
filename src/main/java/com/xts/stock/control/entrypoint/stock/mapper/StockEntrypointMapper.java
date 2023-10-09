package com.xts.stock.control.entrypoint.stock.mapper;

import com.xts.stock.control.entrypoint.stock.dto.DeleteMaterialStockDto;
import com.xts.stock.control.entrypoint.stock.dto.StockDto;
import com.xts.stock.control.entrypoint.stock.dto.StockResponseDto;
import com.xts.stock.control.usecase.stock.domain.DeleteMaterialStockDomain;
import com.xts.stock.control.usecase.stock.domain.StockDomain;

import java.util.List;

public interface StockEntrypointMapper {
    StockDomain registerStockRequestDtoToDomain(StockDto requestDto);

    List<StockResponseDto> getAllStockDomainToEntity(List<StockDomain> responseDomain);

    DeleteMaterialStockDomain deleteMaterialStockDtoToDomain(DeleteMaterialStockDto requestDto);
}
