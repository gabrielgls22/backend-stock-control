package com.xts.stock.control.usecase.stock;

import com.xts.stock.control.entrypoint.interceptor.exceptions.BarcodeAlreadyExistException;
import com.xts.stock.control.entrypoint.interceptor.exceptions.BarcodeDuplicityException;
import com.xts.stock.control.usecase.stock.domain.StockDomain;
import com.xts.stock.control.usecase.stock.gateway.StockGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StockRegisterUseCase {

    private final StockGateway stockGateway;

    public void execute(final StockDomain requestDomain) {

        validateExistingBarCode(requestDomain, stockGateway.getAllStock());

        validateBarCodeDuplicity(requestDomain);

        stockGateway.registerStock(requestDomain);
    }

    private void validateExistingBarCode(final StockDomain requestDomain, final List<StockDomain> allStock) {
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

    private void validateBarCodeDuplicity(final StockDomain requestDomain) {
        final List<String> barCodeList = requestDomain.getMaterialList().get(0)
                .getMaterialsDetails().get(0).getBatchDetails().get(0).getBarCodes();

        Set<String> uniqueBarCodes = new HashSet<>();

        barCodeList.forEach(barCode -> {
            if (!uniqueBarCodes.add(barCode)) {
                throw new BarcodeDuplicityException(barCode);
            }
        });
    }

}
