package com.xts.stock.control.usecase.consumption;

import com.xts.stock.control.usecase.consumption.domain.ConsumptionDomain;
import com.xts.stock.control.usecase.consumption.domain.ConsumptionMaterialDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffMaterialsDomain;
import com.xts.stock.control.usecase.writeoff.gateway.WriteOffGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetConsumptionUseCase {

    private final WriteOffGateway writeOffGateway;

    public List<ConsumptionDomain> execute(final String firstDay, final String lastDay) {

        final List<WriteOffDomain> writeOffDomainList = writeOffGateway.getWriteOffsByFirstAndLastDay(firstDay, lastDay);

        return writeOffDomainList.stream()
                .flatMap(writeOff -> writeOff.getMaterials().stream())
                .collect(Collectors.groupingBy(
                        WriteOffMaterialsDomain::getName,
                        Collectors.groupingBy(material ->
                                                material.getLength() + material.getWidth(),
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        materialsList -> {
                                            final WriteOffMaterialsDomain firstMaterial = materialsList.get(0);
                                            int totalLengthUsed = materialsList.stream()
                                                    .mapToInt(material -> Integer.parseInt(material.getLengthUsed()))
                                                    .sum();
                                            int totalRemovedFromStock = materialsList.size();
                                            return ConsumptionMaterialDomain.builder()
                                                    .supplier(firstMaterial.getSupplier())
                                                    .lengthUsed(totalLengthUsed)
                                                    .removedFromStock(totalRemovedFromStock)
                                                    .width(firstMaterial.getWidth())
                                                    .length(firstMaterial.getLength())
                                                    .build();
                                        }
                                )
                        )
                ))
                .entrySet().stream()
                .map(entry -> ConsumptionDomain.builder()
                        .materialName(entry.getKey())
                        .materials(new ArrayList<>(entry.getValue().values()))
                        .build())
                .collect(Collectors.toList());
    }
}

