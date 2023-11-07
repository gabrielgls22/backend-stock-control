package com.xts.stock.control.usecase.stock;

import com.xts.stock.control.entrypoint.interceptor.exceptions.BarcodeDoesNotExistException;
import com.xts.stock.control.usecase.stock.domain.MaterialDetailsDomain;
import com.xts.stock.control.usecase.stock.domain.StockConsultResponseDomain;
import com.xts.stock.control.usecase.stock.domain.StockDomain;
import com.xts.stock.control.usecase.stock.gateway.StockGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ConsultMaterialStockUseCase {

    private final StockGateway stockGateway;

    public StockConsultResponseDomain execute(final String barCode) {

        final List<StockDomain> allStock = stockGateway.getAllStock();

        AtomicReference<StockConsultResponseDomain> stockConsultResponse = new AtomicReference<>();

        allStock.forEach(stock ->
                stock.getMaterialList().forEach(material ->
                        material.getMaterialsDetails().forEach(materialDetails ->
                                materialDetails.getBatchDetails().forEach(batchDetails -> {
                                    if (batchDetails.getBarCodes().contains(barCode)) {
                                        stockConsultResponse.set(createStockConsultResponse(
                                                stock.getSupplierName(),
                                                material.getMaterialName(),
                                                materialDetails
                                        ));
                                    }
                                })
                        )
                )
        );

        if (Objects.isNull(stockConsultResponse.get())) {
            throw new BarcodeDoesNotExistException(barCode);
        }

        return stockConsultResponse.get();
    }

    private StockConsultResponseDomain createStockConsultResponse(
            final String supplierName,
            final String materialName,
            final MaterialDetailsDomain materialDetailsDomain
    ) {
        return StockConsultResponseDomain.builder()
                .supplierName(supplierName)
                .materialName(materialName)
                .width(materialDetailsDomain.getWidth())
                .length(materialDetailsDomain.getLength())
                .build();
    }
}
