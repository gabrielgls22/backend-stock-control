package com.xts.stock.control.usecase.writeoff;

import com.xts.stock.control.usecase.stock.DeleteMaterialStockUseCase;
import com.xts.stock.control.usecase.stock.GetAllStockUseCase;
import com.xts.stock.control.usecase.stock.domain.DeleteMaterialStockDomain;
import com.xts.stock.control.usecase.stock.domain.StockDomain;
import com.xts.stock.control.usecase.stock.domain.StockMaterialDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;
import com.xts.stock.control.usecase.writeoff.gateway.WriteOffGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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

        requestDomain.getMaterials().forEach(writeOffmaterial -> {
            final List<StockDomain> allStock = getAllStockUseCase.execute();

            if (allStock != null) {
                StockDomain specificStock = allStock.stream()
                        .filter(stock -> stock.getMaterialList().stream()
                                .anyMatch(material -> material.getMaterialsDetails().stream()
                                        .anyMatch(details ->
                                                details.getBatchDetails().stream()
                                                        .anyMatch(batchDetails ->
                                                                batchDetails.getBarCodes().contains(
                                                                        writeOffmaterial.getBarCode())))))
                        .findFirst()
                        .orElse(null);

                if (specificStock != null) {
                    List<StockMaterialDomain> filteredMaterials = specificStock.getMaterialList().stream()
                            .filter(material -> material.getMaterialsDetails().stream()
                                    .anyMatch(details ->
                                            details.getBatchDetails().stream()
                                                    .anyMatch(batchDetails ->
                                                            batchDetails.getBarCodes().contains(
                                                                    writeOffmaterial.getBarCode())))).toList();

                    specificStock.setMaterialList(filteredMaterials);

                    writeOffmaterial.setSupplier(specificStock.getSupplierName());
                    writeOffmaterial.setName(specificStock.getMaterialList().get(0).getMaterialName());
                    writeOffmaterial.
                            setBatch(specificStock.getMaterialList().get(0)
                                    .getMaterialsDetails().get(0).getBatchDetails().get(0).getBatch());

                    final DeleteMaterialStockDomain deleteMaterialStockDomain = DeleteMaterialStockDomain.builder()
                            .supplierName(specificStock.getSupplierName())
                            .materialName(specificStock.getMaterialList().get(0).getMaterialName())
                            .barCode(writeOffmaterial.getBarCode())
                            .build();

                    deleteMaterialStockUseCase.execute(deleteMaterialStockDomain);
                }
            }
        });
    }

}
