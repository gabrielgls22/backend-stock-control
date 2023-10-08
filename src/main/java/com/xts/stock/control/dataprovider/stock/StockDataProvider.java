package com.xts.stock.control.dataprovider.stock;

import com.xts.stock.control.dataprovider.stock.entity.DeleteMaterialStockEntity;
import com.xts.stock.control.dataprovider.stock.entity.StockEntity;
import com.xts.stock.control.dataprovider.stock.mapper.StockRepositoryMapper;
import com.xts.stock.control.dataprovider.stock.repository.StockRepository;
import com.xts.stock.control.usecase.stock.domain.DeleteMaterialStockDomain;
import com.xts.stock.control.usecase.stock.domain.StockDomain;
import com.xts.stock.control.usecase.stock.gateway.StockGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StockDataProvider implements StockGateway {

    private final StockRepositoryMapper stockRepositoryMapper;

    private final StockRepository stockRepository;

    @Override
    public List<StockDomain> getAllStock() {
        final List<StockEntity> responseEntity = stockRepository.getAllStock();


        return stockRepositoryMapper.getAllStockEntityToDomain(responseEntity);
    }

    @Override
    public void deleteMaterial(final DeleteMaterialStockDomain requestDomain) {
        final DeleteMaterialStockEntity requestEntity =
                stockRepositoryMapper.deleteMaterialDomainToEntity(requestDomain);

        stockRepository.deleteMaterial(requestEntity);
    }

    @Override
    public void registerStock(final StockDomain requestDomain) {
        final StockEntity requestEntity =
                stockRepositoryMapper.registerStockDomainToEntity(requestDomain);

        stockRepository.registerStock(requestEntity);
    }
}
