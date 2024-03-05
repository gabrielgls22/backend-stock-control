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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetConsumptionByCodeUseCase {

    private final WriteOffGateway writeOffGateway;

    public List<ConsumptionConsultResponseDomain> execute(final ConsumptionConsultRequestDomain requestDomain) {

        final List<WriteOffDomain> writeOffDomainList = writeOffGateway.getWriteOffsByDate(
                requestDomain.getFirstDay(), requestDomain.getLastDay());

        final List<ConsumptionConsultResponseDomain> consumptionConsultList =

                        writeOffDomainList.stream()
                                .flatMap(writeOff -> writeOff.getMaterials().stream())
                                .filter(material ->
                                        material.getSupplier().equalsIgnoreCase(requestDomain.getSupplierName()))
                                .collect(Collectors.groupingBy(
                                        WriteOffMaterialsDomain::getName,
                                        Collectors.groupingBy(
                                                material -> material.getLength() + material.getWidth(),
                                                Collectors.collectingAndThen(
                                                        Collectors.toList(),
                                                        materialsList -> {
                                                            final WriteOffMaterialsDomain firstMaterial =
                                                                    materialsList.get(0);
                                                            int totalLengthUsed = materialsList.stream()
                                                                    .mapToInt(material ->
                                                                            Integer.parseInt(material.getLengthUsed()))
                                                                    .sum();
                                                            int totalRemovedFromStock = materialsList.size();
                                                            return ConsumptionConsultResponseDomain.builder()
                                                                    .materialName(firstMaterial.getName())
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
                                .flatMap(map -> map.values().stream()).toList();

        if (consumptionConsultList.isEmpty()) {
            throw new StandardException("A data selecionada não possui consumo para o fornecedor: " +
                    requestDomain.getSupplierName());
        }

        return consumptionConsultList;
    }

    private static List<String> getMaterialName(final ConsumptionConsultRequestDomain requestDomain,
                                          final List<SupplierDomain> allSuppliers) {

        return allSuppliers.stream()
                .filter(supplier -> supplier.getSupplierCnpj().equalsIgnoreCase(requestDomain.getSupplierCnpj()))
                .findFirst()
                .map(supplierDomain -> {
                    final List<String> materialsName = new ArrayList<>();
                    supplierDomain.getMaterialList().forEach(materialDomain ->
                            materialsName.add(materialDomain.getName()));

                    return materialsName;
                })
                .orElseThrow(() -> new StandardException("Um erro inesperado ocorreu, se persistir favor consultar " +
                        "o suporte."));
    }
}

