package com.xts.stock.control.dataprovider.stock.repository;

import com.xts.stock.control.dataprovider.stock.entity.DeleteMaterialStockEntity;
import com.xts.stock.control.dataprovider.stock.entity.MaterialDetailsEntity;
import com.xts.stock.control.dataprovider.stock.entity.StockEntity;
import com.xts.stock.control.dataprovider.stock.entity.StockMaterialEntity;
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
        try {
            final StockEntity stockEntity = stockDbRepository.findById(requestEntity.getSupplierName())
                    .orElseThrow(() -> new RuntimeException("Error trying to get stock in db for supplier: " +
                            requestEntity.getSupplierName()));

            List<StockMaterialEntity> materialsToRemove = new ArrayList<>();

            stockEntity.getMaterialList().stream()
                    .filter(material -> requestEntity.getMaterialName().equalsIgnoreCase(material.getMaterialName()))
                    .forEach(filteredMaterial -> {

                        List<MaterialDetailsEntity> updatedDetailsList = new ArrayList<>();

                        filteredMaterial.getMaterialsDetails()
                                .forEach(materialDetails -> {
                                    materialDetails.getBarCodes().removeIf(barCode ->
                                            requestEntity.getBarCode().equalsIgnoreCase(barCode));

                                    materialDetails.setQuantity(materialDetails.getBarCodes().size());

                                    if (!materialDetails.getBarCodes().isEmpty()) {
                                        updatedDetailsList.add(materialDetails);
                                    }
                                });

                        filteredMaterial.setMaterialsDetails(updatedDetailsList);

                        if (filteredMaterial.getMaterialsDetails().isEmpty()) {
                            materialsToRemove.add(filteredMaterial);
                        }
                    });

            stockEntity.getMaterialList().removeAll(materialsToRemove);

            if (stockEntity.getMaterialList().isEmpty()) {
                stockDbRepository.deleteById(stockEntity.getId());
            } else {
                stockDbRepository.save(stockEntity);
            }


        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to delete stock in db, with log: " + e.getMessage());
        }
    }

    public void registerStock(StockEntity requestEntity) {
        try {
            final String materialName = requestEntity.getMaterialList().get(0).getMaterialName();

            final String length = requestEntity.getMaterialList().get(0).getMaterialsDetails().get(0).getLength();
            final String width = requestEntity.getMaterialList().get(0).getMaterialsDetails().get(0).getWidth();
            final List<String> barCodeList = requestEntity.getMaterialList().get(0)
                    .getMaterialsDetails().get(0).getBarCodes();

            stockDbRepository.findById(requestEntity.getId()).ifPresentOrElse(responseEntity -> {
                         List<StockMaterialEntity> specificMaterialEntity = responseEntity.getMaterialList().stream()
                                .filter(materialEntity -> materialName.equalsIgnoreCase(materialEntity.getMaterialName()))
                                .toList();

                        if (!specificMaterialEntity.isEmpty()) {
                            specificMaterialEntity.forEach(materialEntity -> {
                                List<MaterialDetailsEntity> specificMaterialDetails =
                                        materialEntity.getMaterialsDetails().stream()
                                                .filter(materialDetails -> length.equals(materialDetails.getLength()))
                                                .filter(materialDetails -> width.equals(materialDetails.getWidth()))
                                                .toList();

                                if (!specificMaterialDetails.isEmpty()) {
                                    final Integer newQuantity = specificMaterialDetails.get(0).getQuantity() +
                                            barCodeList.size() ;

                                    barCodeList.forEach(barCode ->
                                            specificMaterialDetails.get(0).getBarCodes().add(barCode));

                                    specificMaterialDetails.get(0).setQuantity(newQuantity);

                                    stockDbRepository.save(responseEntity);

                                } else {
                                    materialEntity.getMaterialsDetails().add(
                                            requestEntity.getMaterialList().get(0).getMaterialsDetails().get(0));

                                    stockDbRepository.save(responseEntity);
                                }
                            });

                        } else {
                            responseEntity.getMaterialList().add(requestEntity.getMaterialList().get(0));

                            stockDbRepository.save(responseEntity);
                        }

                    },
                    () -> stockDbRepository.save(requestEntity));


        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to register stock in db, with log: " + e.getMessage());
        }
    }
}
