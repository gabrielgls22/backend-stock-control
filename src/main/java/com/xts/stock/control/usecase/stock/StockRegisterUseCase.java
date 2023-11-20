package com.xts.stock.control.usecase.stock;

import com.xts.stock.control.entrypoint.interceptor.exceptions.BarcodeAlreadyExistException;
import com.xts.stock.control.usecase.stock.domain.StockDomain;
import com.xts.stock.control.usecase.stock.gateway.StockGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockRegisterUseCase {

    private final StockGateway stockGateway;

    public void execute(final StockDomain requestDomain) {

        validateExistingBarCodeList(requestDomain, stockGateway.getAllStock());

        stockGateway.registerStock(requestDomain);
    }

    private void validateExistingBarCodeList(final StockDomain requestDomain, final List<StockDomain> allStock) {
        final List<String> barCodeList = requestDomain.getMaterialList().get(0)
                .getMaterialsDetails().get(0).getBatchDetails().get(0).getBarCodes();

        allStock.forEach(stock -> stock.getMaterialList().forEach(material ->
                material.getMaterialsDetails().forEach(materialDetails ->
                        materialDetails.getBatchDetails().forEach(batch ->
                                batch.getBarCodes().forEach(barCodeResponse ->
                                        barCodeList.forEach(barCode -> {
                                            if (barCodeResponse.equalsIgnoreCase(barCode)) {
                                                throw new BarcodeAlreadyExistException(barCode);
                                            }
                                        })
                                )))));

    }

}
