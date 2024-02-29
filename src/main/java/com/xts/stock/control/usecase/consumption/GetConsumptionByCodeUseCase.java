package com.xts.stock.control.usecase.consumption;

import com.xts.stock.control.entrypoint.interceptor.exceptions.StandardException;
import com.xts.stock.control.usecase.consumption.domain.ConsumptionConsultRequestDomain;
import com.xts.stock.control.usecase.consumption.domain.ConsumptionConsultResponseDomain;
import com.xts.stock.control.usecase.supplier.domain.MaterialDomain;
import com.xts.stock.control.usecase.supplier.domain.SupplierDomain;
import com.xts.stock.control.usecase.supplier.gateway.SupplierGateway;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffMaterialsDomain;
import com.xts.stock.control.usecase.writeoff.gateway.WriteOffGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetConsumptionByCodeUseCase {

    private final WriteOffGateway writeOffGateway;
    private final SupplierGateway supplierGateway;

    public List<ConsumptionConsultResponseDomain> execute(final ConsumptionConsultRequestDomain requestDomain) {
        final List<SupplierDomain> allSuppliers = supplierGateway.getAllSuppliers();
        final String materialName = getMaterialName(requestDomain, allSuppliers);

        final List<WriteOffDomain> writeOffDomainList = writeOffGateway.getWriteOffsByDate(
                requestDomain.getFirstDay(), requestDomain.getLastDay());

        final List<ConsumptionConsultResponseDomain> consumptionConsultList = writeOffDomainList.stream()
                .flatMap(writeOff -> writeOff.getMaterials().stream())
                .filter(material -> material.getName().equals(materialName))
                .collect(Collectors.groupingBy(
                        WriteOffMaterialsDomain::getName,
                        Collectors.groupingBy(
                                material -> material.getLength() + material.getWidth(),
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        materialsList -> {
                                            final WriteOffMaterialsDomain firstMaterial = materialsList.get(0);
                                            int totalLengthUsed = materialsList.stream()
                                                    .mapToInt(material -> Integer.parseInt(material.getLengthUsed()))
                                                    .sum();
                                            int totalRemovedFromStock = materialsList.size();
                                            return ConsumptionConsultResponseDomain.builder()
                                                    .supplierName(firstMaterial.getSupplier())
                                                    .materialName(materialName)
                                                    .width(firstMaterial.getWidth())
                                                    .length(firstMaterial.getLength())
                                                    .lengthUsed(totalLengthUsed)
                                                    .removedFromStock(totalRemovedFromStock)
                                                    .build();
                                        }
                                )
                        )
                ))
                .values().stream()
                .flatMap(map -> map.values().stream())
                .toList();

        if (consumptionConsultList.isEmpty()) {
            throw new StandardException("A data selecionada não possui consumo para o material " + materialName +
                    ", que possui código " + requestDomain.getMaterialCode());
        }

        return consumptionConsultList;
    }

    private static String getMaterialName(final ConsumptionConsultRequestDomain requestDomain,
                                          final List<SupplierDomain> allSuppliers) {

        return allSuppliers.stream()
                .flatMap(supplier -> supplier.getMaterialList().stream())
                .filter(material -> material.getCode().equals(requestDomain.getMaterialCode()))
                .findFirst()
                .map(MaterialDomain::getName)
                .orElseThrow(() -> new StandardException("O material com código " +
                        requestDomain.getMaterialCode() + " não existe."));
    }
}

