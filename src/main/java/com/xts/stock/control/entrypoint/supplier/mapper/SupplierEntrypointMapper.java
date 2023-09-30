package com.xts.stock.control.entrypoint.supplier.mapper;

import com.xts.stock.control.usecase.supplier.domain.SupplierRequestDomain;
import com.xts.stock.control.entrypoint.supplier.dto.SupplierRequestDto;

public interface SupplierEntrypointMapper {

    SupplierRequestDomain requestDtoToDomain(SupplierRequestDto requestDto);
}
