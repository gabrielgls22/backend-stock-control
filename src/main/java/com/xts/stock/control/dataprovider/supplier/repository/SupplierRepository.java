package com.xts.stock.control.dataprovider.supplier.repository;

import com.xts.stock.control.dataprovider.supplier.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SupplierRepository {

    private final SupplierDbRepository supplierDbRepository;

    public void createNewSupplier(final SupplierEntity requestEntity) {

        try{
            supplierDbRepository.save(requestEntity);

        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to save new supplier in db, with log: " + e.getMessage());
        }

    }

    public List<SupplierEntity> getAllSuppliers() {
        try{
           return supplierDbRepository.findAll();

        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to save new supplier in db, with log: " + e.getMessage());
        }
    }

    public void updateSupplier(final SupplierUpdateRequestEntity requestEntity) {
        try {
            final SupplierEntity supplierEntityUpdated = supplierDbRepository.findById(requestEntity.getCnpj())
                    .orElseThrow(() -> new RuntimeException("Error trying to get supplier in db, with log: "));

            supplierEntityUpdated.setId(requestEntity.getNewCnpj());
            supplierEntityUpdated.setSupplierName(requestEntity.getNewSupplier());

            supplierDbRepository.deleteById(requestEntity.getCnpj());
            supplierDbRepository.save(supplierEntityUpdated);

        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to update supplier in db, with log: " + e.getMessage());
        }
    }

    public void updateMaterial(final MaterialUpdateRequestEntity requestEntity) {
        final SupplierEntity supplierMaterialUpdated = supplierDbRepository.findById(requestEntity.getCnpj())
                .orElseThrow(() -> new RuntimeException("Error trying to get supplier in db, with log: "));

        supplierMaterialUpdated.getMaterialList().forEach(material -> {
            if (requestEntity.getCode().equals(material.getCode())) {
                material.setName(requestEntity.getName());
                material.setDescription(requestEntity.getDescription());
            }
        });

        supplierDbRepository.save(supplierMaterialUpdated);
    }

    public void deleteSupplier(final String supplierCnpj) {
        try {
            supplierDbRepository.deleteById(supplierCnpj);

        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to delete supplier in db, with log: " + e.getMessage());
        }
    }

    public void deleteMaterial(final MaterialDeleteRequestEntity requestEntity) {
        try {
            final SupplierEntity supplierEntity =
                    supplierDbRepository.findById(requestEntity.getSupplierCnpj())
                    .orElseThrow(() -> new RuntimeException("Error trying to get supplier in db, with log: "));

            supplierEntity.getMaterialList().removeIf(material ->
                    requestEntity.getMaterialCode().equals(material.getCode()));

            supplierDbRepository.save(supplierEntity);

        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to delete material in db, with log: " + e.getMessage());
        }
    }

    public void addMaterial(final MaterialAddRequestEntity materialAddRequestEntity) {
        try {
            final SupplierEntity supplierEntity =
                    supplierDbRepository.findById(materialAddRequestEntity.getSupplierCnpj())
                            .orElseThrow(() -> new RuntimeException("Error trying to get supplier in db, with log: "));

            final MaterialEntity addMaterial = MaterialEntity.builder()
                    .code(materialAddRequestEntity.getCode())
                    .name(materialAddRequestEntity.getName())
                    .description(materialAddRequestEntity.getDescription())
                    .build();

            supplierEntity.getMaterialList().add(addMaterial);

            supplierDbRepository.save(supplierEntity);

        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to add material to a supplier in db, with log: " + e.getMessage());
        }
    }
}
