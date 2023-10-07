package com.xts.stock.control.dataprovider.stock.mapper;

import com.xts.stock.control.dataprovider.stock.entity.DeleteMaterialStockEntity;
import com.xts.stock.control.dataprovider.stock.entity.MaterialDetailsEntity;
import com.xts.stock.control.dataprovider.stock.entity.StockEntity;
import com.xts.stock.control.dataprovider.stock.entity.StockMaterialEntity;
import com.xts.stock.control.usecase.stock.domain.DeleteMaterialStockDomain;
import com.xts.stock.control.usecase.stock.domain.MaterialDetailsDomain;
import com.xts.stock.control.usecase.stock.domain.StockDomain;
import com.xts.stock.control.usecase.stock.domain.StockMaterialDomain;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class StockRepositoryMapperImpl implements StockRepositoryMapper{
    @Override
    public List<StockDomain> getAllStockEntityToDomain(final List<StockEntity> responseEntity) {
        final List<StockDomain> stockDomainList = new ArrayList<>();

        if (Objects.nonNull(responseEntity) && !responseEntity.isEmpty()) {

            responseEntity.forEach(stockEntity -> {
                final StockDomain stockDomain = StockDomain.builder()
                        .supplierName(stockEntity.getId())
                        .materialList(responseStockMaterialListEntityToDomain(stockEntity.getMaterialList()))
                        .build();

                stockDomainList.add(stockDomain);
            });
        }

        return stockDomainList;
    }

    @Override
    public DeleteMaterialStockEntity deleteMaterialDomainToEntity(final DeleteMaterialStockDomain requestDomain) {
        final DeleteMaterialStockEntity.DeleteMaterialStockEntityBuilder deleteMaterialStockEntityBuilder =
                DeleteMaterialStockEntity.builder();

        if (Objects.nonNull(requestDomain)) {
            deleteMaterialStockEntityBuilder.supplierName(requestDomain.getSupplierName());
            deleteMaterialStockEntityBuilder.materialName(requestDomain.getMaterialName());
            deleteMaterialStockEntityBuilder.barCode(requestDomain.getBarCode());
        }

        return deleteMaterialStockEntityBuilder.build();
    }

    protected List<StockMaterialDomain> responseStockMaterialListEntityToDomain(
            final List<StockMaterialEntity> stockMaterialEntityList) {

        final List<StockMaterialDomain> stockMaterialDomainList = new ArrayList<>();

        if (Objects.nonNull(stockMaterialEntityList) && !stockMaterialEntityList.isEmpty()) {

            stockMaterialEntityList.forEach(stockMaterialEntity -> {
                final StockMaterialDomain stockMaterialDomain = StockMaterialDomain.builder()
                        .materialName(stockMaterialEntity.getMaterialName())
                        .materialsDetails(
                                responseMaterialDetailsListEntityToDomain(stockMaterialEntity.getMaterialsDetails()))
                        .build();

                stockMaterialDomainList.add(stockMaterialDomain);
            });

        }
        return stockMaterialDomainList;
    }

    private List<MaterialDetailsDomain> responseMaterialDetailsListEntityToDomain(
            final List<MaterialDetailsEntity> materialDetailsEntityList) {

        final List<MaterialDetailsDomain> materialDetailsDomainList = new ArrayList<>();

        if (Objects.nonNull(materialDetailsEntityList) && !materialDetailsEntityList.isEmpty()) {

            materialDetailsEntityList.forEach(materialDetailsEntity -> {
                final MaterialDetailsDomain materialDetailsDomain = MaterialDetailsDomain.builder()
                        .batch(materialDetailsEntity.getBatch())
                        .length(materialDetailsEntity.getLength())
                        .width(materialDetailsEntity.getWidth())
                        .quantity(materialDetailsEntity.getQuantity())
                        .barCodes(materialDetailsEntity.getBarCodes())
                        .build();

                materialDetailsDomainList.add(materialDetailsDomain);
            });

        }

        return materialDetailsDomainList;
    }
}
