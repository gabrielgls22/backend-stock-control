package com.xts.stock.control.dataprovider.stock.repository;

import com.xts.stock.control.dataprovider.stock.entity.*;
import com.xts.stock.control.entrypoint.interceptor.exceptions.BarcodeDoesNotExistException;
import com.xts.stock.control.entrypoint.interceptor.exceptions.StandardException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@RequiredArgsConstructor
public class StockRepository {

    private final StockDbRepository stockDbRepository;

    public List<StockEntity> getAllStock() {
        try{
            return stockDbRepository.findAll();

        } catch (final StandardException e) {
            throw new StandardException("Erro ao buscar o estoque.");
        }
    }

    public void deleteMaterial(final DeleteMaterialStockEntity requestEntity) {
        try {
            final StockEntity stockEntity = stockDbRepository.findById(requestEntity.getSupplierName())
                    .orElseThrow(() -> new StandardException("Erro ao buscar o estoque."));

            stockEntity.getMaterialList().stream()
                    .filter(material -> requestEntity.getMaterialName().equalsIgnoreCase(material.getMaterialName()))
                    .forEach(filteredMaterial -> {

                        filteredMaterial.getMaterialsDetails().removeIf(materialDetails -> {
                            if (requestEntity.getWidth().equalsIgnoreCase(materialDetails.getWidth()) &&
                                    requestEntity.getLength().equalsIgnoreCase(materialDetails.getLength())) {

                                final AtomicInteger quantity = new AtomicInteger();
                                AtomicBoolean isAnExistingBarcode = new AtomicBoolean(false);

                                materialDetails.getBatchDetails().forEach(batchDetailsEntity -> {
                                    batchDetailsEntity.getBarCodes().forEach(barCode -> {
                                        if (barCode.equalsIgnoreCase(requestEntity.getBarCode())) {
                                            isAnExistingBarcode.set(true);
                                        }
                                    });

                                    batchDetailsEntity.getBarCodes().removeIf(barCode ->
                                            requestEntity.getBarCode().equalsIgnoreCase(barCode));

                                    quantity.addAndGet(batchDetailsEntity.getBarCodes().size());
                                });

                                if (!isAnExistingBarcode.get()) {
                                    throw new BarcodeDoesNotExistException(
                                            "O cód. de barras " + requestEntity.getBarCode() +
                                                    " não existe para o material " + requestEntity.getMaterialName() +
                                                    " do fornecedor " + requestEntity.getSupplierName()
                                    );
                                }

                                materialDetails.getBatchDetails().removeIf(batch ->
                                        batch.getBarCodes().isEmpty());

                                materialDetails.setQuantity(quantity.get());

                                return materialDetails.getQuantity() == 0;
                            }
                            return false;
                        });

                        filteredMaterial.getMaterialsDetails().removeIf(
                                materialDetails -> materialDetails.getQuantity() == 0);
                    });

            stockEntity.getMaterialList().removeIf(
                    filteredMaterial -> filteredMaterial.getMaterialsDetails().isEmpty());

            if (stockEntity.getMaterialList().isEmpty()) {
                stockDbRepository.deleteById(stockEntity.getId());
            } else {
                stockDbRepository.save(stockEntity);
            }
        } catch (final StandardException e) {
            throw new StandardException("Erro ao deletar o material do estoque.");
        }
    }


    public void registerStock(final StockEntity requestEntity) {
        try {
            final String materialName = requestEntity.getMaterialList().get(0).getMaterialName();

            final String length = requestEntity.getMaterialList().get(0).getMaterialsDetails().get(0).getLength();
            final String width = requestEntity.getMaterialList().get(0).getMaterialsDetails().get(0).getWidth();
            final String batch = requestEntity.getMaterialList().get(0)
                    .getMaterialsDetails().get(0).getBatchDetails().get(0).getBatch();

            stockDbRepository.findById(requestEntity.getId()).ifPresentOrElse(responseEntity -> {

                        List<StockMaterialEntity> specificMaterialEntity = responseEntity.getMaterialList().stream()
                                .filter(materialEntity ->
                                        materialName.equalsIgnoreCase(materialEntity.getMaterialName()))
                                .toList();

                        if (!specificMaterialEntity.isEmpty()) {
                            specificMaterialEntity.forEach(materialEntity -> {
                                List<MaterialDetailsEntity> specificMaterialDetails =
                                        materialEntity.getMaterialsDetails().stream()
                                                .filter(materialDetails -> length.equals(materialDetails.getLength()))
                                                .filter(materialDetails -> width.equals(materialDetails.getWidth()))
                                                .toList();

                                if (!specificMaterialDetails.isEmpty()) {
                                    List<BatchDetailsEntity> specificBatchDetails =
                                            specificMaterialDetails.get(0).getBatchDetails().stream()
                                                    .filter(batchDetails -> batch.equals(batchDetails.getBatch()))
                                                    .toList();

                                    final AtomicInteger newQuantity = new AtomicInteger(
                                            specificMaterialDetails.get(0).getQuantity());

                                    final List<String> barCodesList = requestEntity.getMaterialList().get(0)
                                            .getMaterialsDetails().get(0).getBatchDetails().get(0).getBarCodes();

                                    newQuantity.addAndGet(barCodesList.size());

                                    specificMaterialDetails.get(0).setQuantity(newQuantity.get());

                                    if (!specificBatchDetails.isEmpty()) {

                                        specificBatchDetails.get(0).getBarCodes().addAll(barCodesList);
                                        stockDbRepository.save(responseEntity);

                                    } else {
                                        specificMaterialDetails.get(0).getBatchDetails().add(
                                                requestEntity.getMaterialList().get(0).getMaterialsDetails().get(0)
                                                        .getBatchDetails().get(0));

                                        stockDbRepository.save(responseEntity);
                                    }

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


        } catch (final StandardException e) {
            throw new StandardException("Erro ao cadastrar material no estoque.");
        }
    }
}
