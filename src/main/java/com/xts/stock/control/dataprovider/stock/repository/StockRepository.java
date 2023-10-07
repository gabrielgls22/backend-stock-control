package com.xts.stock.control.dataprovider.stock.repository;

import com.xts.stock.control.dataprovider.stock.entity.DeleteMaterialStockEntity;
import com.xts.stock.control.dataprovider.stock.entity.MaterialDetailsEntity;
import com.xts.stock.control.dataprovider.stock.entity.StockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StockRepository {

    private final StockDbRepository stockDbRepository;

    public List<StockEntity> getAllStock() {
        try{
            return stockDbRepository.findAll();

        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to get all stock in db, with log: " + e.getMessage());
        }
    }

    public void deleteMaterial(final DeleteMaterialStockEntity requestEntity) {
        try{
            final StockEntity stockEntity = stockDbRepository.findById(requestEntity.getSupplierName())
                    .orElseThrow(() -> new RuntimeException("Error trying to get stock in db for supplier: " +
                            requestEntity.getSupplierName()));

            stockEntity.getMaterialList().stream()
                    .filter(material -> requestEntity.getMaterialName().equalsIgnoreCase(material.getMaterialName()))
                    .forEach(filteredMaterial -> {

                        List<MaterialDetailsEntity> updatedDetailsList = new ArrayList<>();

                        filteredMaterial.getMaterialsDetails()
                                .forEach(materialDetails -> {
                                    materialDetails.getBarCodes().removeIf(barCode ->
                                            requestEntity.getBarCode().equalsIgnoreCase(barCode));

                                    if (!materialDetails.getBarCodes().isEmpty()) {
                                        updatedDetailsList.add(materialDetails);
                                    }
                                });

                        filteredMaterial.setMaterialsDetails(updatedDetailsList);
                    });

            stockDbRepository.save(stockEntity);


        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to get all stock in db, with log: " + e.getMessage());
        }
    }
}
