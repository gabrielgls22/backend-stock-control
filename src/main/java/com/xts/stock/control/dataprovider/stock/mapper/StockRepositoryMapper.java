package com.xts.stock.control.dataprovider.stock.mapper;

import com.xts.stock.control.dataprovider.stock.entity.DeleteMaterialStockEntity;
import com.xts.stock.control.dataprovider.stock.entity.StockEntity;
import com.xts.stock.control.usecase.stock.domain.DeleteMaterialStockDomain;
import com.xts.stock.control.usecase.stock.domain.StockDomain;

import java.util.List;

public interface StockRepositoryMapper {
    List<StockDomain> getAllStockEntityToDomain(List<StockEntity> responseEntity);

    DeleteMaterialStockEntity deleteMaterialDomainToEntity(DeleteMaterialStockDomain requestDomain);
}
