package com.xts.stock.control.usecase.writeoff;

import com.xts.stock.control.entrypoint.interceptor.exceptions.BarcodeDoesNotExistException;
import com.xts.stock.control.entrypoint.interceptor.exceptions.BarcodeDuplicityException;
import com.xts.stock.control.entrypoint.interceptor.exceptions.StandardException;
import com.xts.stock.control.usecase.stock.DeleteMaterialStockUseCase;
import com.xts.stock.control.usecase.stock.GetAllStockUseCase;
import com.xts.stock.control.usecase.stock.domain.*;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffMaterialsDomain;
import com.xts.stock.control.usecase.writeoff.gateway.WriteOffGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WriteOffRegisterUseCase {

    private final WriteOffGateway writeOffGateway;

    private final GetAllStockUseCase getAllStockUseCase;

    private final DeleteMaterialStockUseCase deleteMaterialStockUseCase;

    public void execute(final WriteOffDomain requestDomain) {

        setMaterialsAttributes(requestDomain);

        writeOffGateway.registerWriteOff(requestDomain);
    }

    private void setMaterialsAttributes(final WriteOffDomain requestDomain) {
        requestDomain.setWriteOffDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        validateBarCodeDuplicity(requestDomain.getMaterials());

        final List<StockDomain> allStock = getAllStockUseCase.execute();

        if (Objects.isNull(allStock)) {
            throw new StandardException("Erro ao buscar o estoque.");
        }

        validateIfMaterialExistInStock(requestDomain.getMaterials(), allStock);

        requestDomain.getMaterials().forEach(writeOffMaterial -> {

                StockDomain specificStock = allStock.stream()
                        .filter(stock -> stock.getMaterialList().stream()
                                .anyMatch(material -> material.getMaterialsDetails().stream()
                                        .anyMatch(details ->
                                                details.getBatchDetails().stream()
                                                        .anyMatch(batchDetails ->
                                                                batchDetails.getBarCodes().contains(
                                                                        writeOffMaterial.getBarCode())))))
                        .findFirst()
                        .orElse(null);

                if (specificStock != null) {
                    List<StockMaterialDomain> filteredMaterials = specificStock.getMaterialList().stream()
                            .filter(material -> material.getMaterialsDetails().stream()
                                    .anyMatch(details ->
                                            details.getBatchDetails().stream()
                                                    .anyMatch(batchDetails ->
                                                            batchDetails.getBarCodes().contains(
                                                                    writeOffMaterial.getBarCode())))).toList();

                    List<MaterialDetailsDomain> filteredMaterialsDetails = specificStock.getMaterialList().stream()
                            .flatMap(material -> material.getMaterialsDetails().stream()
                                    .filter(materialDetails -> materialDetails.getBatchDetails().stream()
                                            .anyMatch(batch -> batch.getBarCodes().stream()
                                                    .anyMatch(barCode ->
                                                            barCode.equalsIgnoreCase(
                                                                    writeOffMaterial.getBarCode()))))).toList();

                    List<BatchDetailsDomain> specificBatchDetails = specificStock.getMaterialList().stream()
                            .flatMap(material -> material.getMaterialsDetails().stream()
                                    .flatMap(materialDetails -> materialDetails.getBatchDetails().stream()
                                            .filter(batchDetails -> batchDetails.getBarCodes().stream()
                                                    .anyMatch(barCode ->
                                                            barCode.equalsIgnoreCase(writeOffMaterial.getBarCode()))
                                            )
                                    )
                            )
                            .toList();


                    writeOffMaterial.setSupplier(specificStock.getSupplierName());
                    writeOffMaterial.setName(filteredMaterials.get(0).getMaterialName());
                    writeOffMaterial.setLength(filteredMaterialsDetails.get(0).getLength());
                    writeOffMaterial.setWidth(filteredMaterialsDetails.get(0).getLength());
                    writeOffMaterial.
                            setBatch(specificBatchDetails.get(0).getBatch());

                    final DeleteMaterialStockDomain deleteMaterialStockDomain = DeleteMaterialStockDomain.builder()
                            .supplierName(specificStock.getSupplierName())
                            .materialName(filteredMaterials.get(0).getMaterialName())
                            .width(filteredMaterialsDetails.get(0).getWidth())
                            .length(filteredMaterialsDetails.get(0).getLength())
                            .barCode(writeOffMaterial.getBarCode())
                            .build();

                    deleteMaterialStockUseCase.execute(deleteMaterialStockDomain);
                }
        });
    }

    private void validateIfMaterialExistInStock(List<WriteOffMaterialsDomain> writeOffMaterials,
                                                List<StockDomain> allStock) {

        writeOffMaterials.forEach(writeOffMaterial -> {
            boolean isAnExistingBarCode = allStock.stream()
                    .flatMap(stock -> stock.getMaterialList().stream())
                    .flatMap(material -> material.getMaterialsDetails().stream())
                    .flatMap(materialDetails -> materialDetails.getBatchDetails().stream())
                    .anyMatch(batch -> batch.getBarCodes().contains(writeOffMaterial.getBarCode()));

            if (!isAnExistingBarCode) {
                throw new BarcodeDoesNotExistException(writeOffMaterial.getBarCode());
            }
        });
    }

    private void validateBarCodeDuplicity(final List<WriteOffMaterialsDomain> writeOffMaterialsDomainList) {
        final List<String> barCodeList = new ArrayList<>();

        writeOffMaterialsDomainList.forEach(writeOffMaterial -> barCodeList.add(writeOffMaterial.getBarCode()));

        Set<String> uniqueBarCodes = new HashSet<>();

        barCodeList.forEach(barCode -> {
            if (!uniqueBarCodes.add(barCode)) {
                throw new BarcodeDuplicityException(barCode);
            }
        });
    }
}
