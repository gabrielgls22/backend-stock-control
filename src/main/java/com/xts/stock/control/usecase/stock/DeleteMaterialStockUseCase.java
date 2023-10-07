package com.xts.stock.control.usecase.stock;

import com.xts.stock.control.usecase.stock.domain.DeleteMaterialStockDomain;
import com.xts.stock.control.usecase.stock.gateway.StockGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMaterialStockUseCase {

    private final StockGateway stockGateway;

    public void execute(final DeleteMaterialStockDomain requestDomain) {

        stockGateway.deleteMaterial(requestDomain);
    }
}
