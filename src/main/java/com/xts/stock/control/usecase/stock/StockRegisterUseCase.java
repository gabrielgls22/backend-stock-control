package com.xts.stock.control.usecase.stock;

import com.xts.stock.control.usecase.stock.domain.StockDomain;
import com.xts.stock.control.usecase.stock.gateway.StockGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockRegisterUseCase {

    private final StockGateway stockGateway;
    public void execute(final StockDomain requestDomain) {

        stockGateway.registerStock(requestDomain);
    }
}
