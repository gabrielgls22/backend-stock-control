package com.xts.stock.control.entrypoint.supplier.mapper;

import com.xts.stock.control.entrypoint.supplier.dto.*;
import com.xts.stock.control.usecase.supplier.domain.*;

import java.util.List;

public interface SupplierEntrypointMapper {

    SupplierDomain createSupplierRequestDtoToDomain(SupplierDto requestDto);

    List<SupplierDto> getAllSuppliersDomainToDto(List<SupplierDomain> responseDomain);

    SupplierUpdateRequestDomain updateSupplierRequestDtoToDomain(SupplierUpdateRequestDto requestDto);

    MaterialUpdateRequestDomain updateMaterialRequestDtoToDomain(MaterialUpdateRequestDto requestDto);

    MaterialDeleteRequestDomain deleteMaterialRequestDtoToDomain(MaterialDeleteRequestDto requestDto);

    MaterialAddRequestDomain addMaterialRequestDtoToDomain(MaterialAddRequestDto requestDto);
}
