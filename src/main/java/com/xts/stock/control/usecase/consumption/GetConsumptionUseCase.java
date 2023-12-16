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

        final List<WriteOffDomain> writeOffDomainList = writeOffGateway.getAllWriteOffs(firstDay, lastDay);

        return writeOffDomainList.stream()
                .flatMap(writeOff -> writeOff.getMaterials().stream())
                .collect(Collectors.groupingBy(
                        WriteOffMaterialsDomain::getSupplier,
                        Collectors.groupingBy(material ->
                                        material.getName() + material.getLength() + material.getWidth(),
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        materialsList -> {
                                            final WriteOffMaterialsDomain firstMaterial = materialsList.get(0);
                                            int totalLengthUsed = materialsList.stream()
                                                    .mapToInt(material -> Integer.parseInt(material.getLengthUsed()))
                                                    .sum();
                                            int totalremovedFromStock = materialsList.size();
                                            return ConsumptionMaterialDomain.builder()
                                                    .name(firstMaterial.getName())
                                                    .lengthUsed(totalLengthUsed)
                                                    .removedFromStock(totalremovedFromStock)
                                                    .width(firstMaterial.getWidth())
                                                    .length(firstMaterial.getLength())
                                                    .build();
                                        }
                                )
                        )
                ))
                .entrySet().stream()
                .map(entry -> ConsumptionDomain.builder()
                        .supplier(entry.getKey())
                        .materials(new ArrayList<>(entry.getValue().values()))
                        .build())
                .collect(Collectors.toList());
    }
}

