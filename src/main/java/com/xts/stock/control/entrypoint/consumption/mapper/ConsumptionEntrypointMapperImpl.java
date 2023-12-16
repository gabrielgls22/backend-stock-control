package com.xts.stock.control.entrypoint.consumption.mapper;

import com.xts.stock.control.entrypoint.consumption.dto.ConsumptionDto;
import com.xts.stock.control.entrypoint.consumption.dto.ConsumptionMaterialDto;
import com.xts.stock.control.usecase.consumption.domain.ConsumptionDomain;
import com.xts.stock.control.usecase.consumption.domain.ConsumptionMaterialDomain;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsumptionEntrypointMapperImpl implements ConsumptionEntrypointMapper {

    @Override
    public List<ConsumptionDto> getConsumptionDomainToDto(final List<ConsumptionDomain> responseDomain) {
        final List<ConsumptionDto> consumptionDtoList = new ArrayList<>();

        responseDomain.forEach(consumptionDomain -> {
            final ConsumptionDto consumptionDto = ConsumptionDto.builder()
                    .supplier(consumptionDomain.getSupplier())
                    .materials(responseConsumptionMaterialDomainToDto(consumptionDomain.getMaterials()))
                    .build();

            consumptionDtoList.add(consumptionDto);
        });

        return consumptionDtoList;
    }

    private List<ConsumptionMaterialDto> responseConsumptionMaterialDomainToDto(
            final List<ConsumptionMaterialDomain> consumptionMaterialDomainList) {
        final List<ConsumptionMaterialDto> consumptionMaterialDtoList = new ArrayList<>();

        consumptionMaterialDomainList.forEach(consumptionMaterialDomain -> {
            final ConsumptionMaterialDto consumptionMaterialDto = ConsumptionMaterialDto.builder()
                    .name(consumptionMaterialDomain.getName())
                    .lengthUsed(consumptionMaterialDomain.getLengthUsed())
                    .removedFromStock(consumptionMaterialDomain.getRemovedFromStock())
                    .width(consumptionMaterialDomain.getWidth())
                    .length(consumptionMaterialDomain.getLength())
                    .widthAndLength(String.format("%s x %s",
                            consumptionMaterialDomain.getWidth(), consumptionMaterialDomain.getLength()))
                    .build();

            consumptionMaterialDtoList.add(consumptionMaterialDto);
        });


        return consumptionMaterialDtoList;
    }
}
