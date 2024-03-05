package com.xts.stock.control.entrypoint.consumption.mapper;

import com.xts.stock.control.entrypoint.consumption.dto.ConsumptionConsultRequestDto;
import com.xts.stock.control.entrypoint.consumption.dto.ConsumptionConsultResponseDto;
import com.xts.stock.control.entrypoint.consumption.dto.ConsumptionDto;
import com.xts.stock.control.entrypoint.consumption.dto.ConsumptionMaterialDto;
import com.xts.stock.control.usecase.consumption.domain.ConsumptionConsultRequestDomain;
import com.xts.stock.control.usecase.consumption.domain.ConsumptionConsultResponseDomain;
import com.xts.stock.control.usecase.consumption.domain.ConsumptionDomain;
import com.xts.stock.control.usecase.consumption.domain.ConsumptionMaterialDomain;
import com.xts.stock.control.utils.Utils;
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
                    .materialName(consumptionDomain.getMaterialName())
                    .materials(responseConsumptionMaterialDomainToDto(consumptionDomain.getMaterials()))
                    .build();

            consumptionDtoList.add(consumptionDto);
        });

        return consumptionDtoList;
    }

    @Override
    public ConsumptionConsultRequestDomain consumptionRequestDtoToDomain(
            final ConsumptionConsultRequestDto requestDto) {

        return ConsumptionConsultRequestDomain.builder()
                .supplierName(requestDto.getSupplierName())
                .supplierCnpj(Utils.removeSignals(requestDto.getSupplierCnpj()))
                .firstDay(requestDto.getFirstDay())
                .lastDay(requestDto.getLastDay())
                .build();
    }

    @Override
    public List<ConsumptionConsultResponseDto> consumptionResponseDomainToDto(
            final List<ConsumptionConsultResponseDomain> responseDomain) {

        final List<ConsumptionConsultResponseDto> consultResponseDtoList = new ArrayList<>();

        responseDomain.forEach(consumptionConsult -> {
            final ConsumptionConsultResponseDto responseDto = ConsumptionConsultResponseDto.builder()
                    .materialName(consumptionConsult.getMaterialName())
                    .widthAndLength(String.format("%s x %s",
                            consumptionConsult.getWidth(), consumptionConsult.getLength()))
                    .lengthUsed(consumptionConsult.getLengthUsed())
                    .removedFromStock(consumptionConsult.getRemovedFromStock())
                    .build();

            consultResponseDtoList.add(responseDto);
        });

        return consultResponseDtoList;
    }

    private List<ConsumptionMaterialDto> responseConsumptionMaterialDomainToDto(
            final List<ConsumptionMaterialDomain> consumptionMaterialDomainList) {
        final List<ConsumptionMaterialDto> consumptionMaterialDtoList = new ArrayList<>();

        consumptionMaterialDomainList.forEach(consumptionMaterialDomain -> {
            final ConsumptionMaterialDto consumptionMaterialDto = ConsumptionMaterialDto.builder()
                    .supplier(consumptionMaterialDomain.getSupplier())
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
