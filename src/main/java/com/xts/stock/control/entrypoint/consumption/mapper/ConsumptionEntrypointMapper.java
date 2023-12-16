package com.xts.stock.control.entrypoint.consumption.mapper;

import com.xts.stock.control.entrypoint.consumption.dto.ConsumptionDto;
import com.xts.stock.control.usecase.consumption.domain.ConsumptionDomain;

import java.util.List;

public interface ConsumptionEntrypointMapper {
    List<ConsumptionDto> getConsumptionDomainToDto(List<ConsumptionDomain> responseDomain);
}
