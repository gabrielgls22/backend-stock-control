package com.xts.stock.control.entrypoint.costumer.mapper;

import com.xts.stock.control.entrypoint.costumer.dto.*;
import com.xts.stock.control.usecase.costumer.domain.*;
import com.xts.stock.control.usecase.supplier.domain.*;

import java.util.List;

public interface CostumerEntrypointMapper {

    CostumerDomain createCostumerRequestDtoToDomain(CostumerDto requestDto);

    List<CostumerDto> getAllCostumersDomainToDto(List<CostumerDomain> responseDomain);

    CostumerUpdateRequestDomain updateCostumerRequestDtoToDomain(CostumerUpdateRequestDto requestDto);

    TagUpdateRequestDomain updateTagRequestDtoToDomain(TagUpdateRequestDto requestDto);

    TagDeleteRequestDomain deleteTagRequestDtoToDomain(TagDeleteRequestDto requestDto);

    TagAddRequestDomain addTagRequestDtoToDomain(TagAddRequestDto requestDto);
}
