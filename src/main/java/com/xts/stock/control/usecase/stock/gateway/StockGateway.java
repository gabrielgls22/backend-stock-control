package com.xts.stock.control.usecase.stock.gateway;

import com.xts.stock.control.usecase.stock.domain.DeleteMaterialStockDomain;
import com.xts.stock.control.usecase.stock.domain.StockDomain;

import java.util.List;

public interface StockGateway {

    List<StockDomain> getAllStock();

    void deleteMaterial(DeleteMaterialStockDomain requestDomain);

    void registerStock(StockDomain requestDomain);
}
