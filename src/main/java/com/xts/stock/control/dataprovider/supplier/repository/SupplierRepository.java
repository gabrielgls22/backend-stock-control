package com.xts.stock.control.dataprovider.supplier.repository;

import com.xts.stock.control.dataprovider.supplier.entity.*;
import com.xts.stock.control.entrypoint.interceptor.exceptions.StandardException;
import com.xts.stock.control.entrypoint.interceptor.exceptions.SupplierAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SupplierRepository {

    private final SupplierDbRepository supplierDbRepository;

    public void createNewSupplier(final SupplierEntity requestEntity) {

        try {
            supplierDbRepository.findById(requestEntity.getId()).ifPresentOrElse(supplier ->
                    {
                        throw new SupplierAlreadyExistException();
                    },

                    () -> supplierDbRepository.save(requestEntity));

        } catch (final StandardException e) {
            throw new StandardException("Erro ao salvar fornecedor.");
        }
    }

    public List<SupplierEntity> getAllSuppliers() {
        try{
           return supplierDbRepository.findAll();

        } catch (final StandardException e) {
            throw new StandardException("Erro ao buscar fornecedores.");
        }
    }

    public void updateSupplier(final SupplierUpdateRequestEntity requestEntity) {
        try {
            final SupplierEntity supplierEntityUpdated = supplierDbRepository.findById(requestEntity.getCnpj())
                    .orElseThrow(() -> new StandardException("Erro ao buscar fornecedores."));

            supplierEntityUpdated.setId(requestEntity.getNewCnpj());
            supplierEntityUpdated.setSupplierName(requestEntity.getNewSupplier());

            supplierDbRepository.deleteById(requestEntity.getCnpj());
            supplierDbRepository.save(supplierEntityUpdated);

        } catch (final StandardException e) {
            throw new StandardException("Erro ao atualizar fornecedor.");
        }
    }

    public void updateMaterial(final MaterialUpdateRequestEntity requestEntity) {
        final SupplierEntity supplierMaterialUpdated = supplierDbRepository.findById(requestEntity.getCnpj())
                .orElseThrow(() -> new StandardException("Erro ao buscar fornecedores."));

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

        } catch (final StandardException e) {
            throw new StandardException("Erro ao deletar fornecedor.");
        }
    }

    public void deleteMaterial(final MaterialDeleteRequestEntity requestEntity) {
        try {
            final SupplierEntity supplierEntity =
                    supplierDbRepository.findById(requestEntity.getSupplierCnpj())
                    .orElseThrow(() -> new StandardException("Erro ao buscar fornecedores."));

            supplierEntity.getMaterialList().removeIf(material ->
                    requestEntity.getMaterialCode().equals(material.getCode()));

            supplierDbRepository.save(supplierEntity);

        } catch (final StandardException e) {
            throw new StandardException("Erro ao deletar o material do fornecedor.");
        }
    }

    public void addMaterial(final MaterialAddRequestEntity materialAddRequestEntity) {
        try {
            final SupplierEntity supplierEntity =
                    supplierDbRepository.findById(materialAddRequestEntity.getSupplierCnpj())
                            .orElseThrow(() -> new StandardException("Erro ao buscar fornecedores."));

            final MaterialEntity addMaterial = MaterialEntity.builder()
                    .code(materialAddRequestEntity.getCode())
                    .name(materialAddRequestEntity.getName())
                    .description(materialAddRequestEntity.getDescription())
                    .build();

            supplierEntity.getMaterialList().add(addMaterial);

            supplierDbRepository.save(supplierEntity);

        } catch (final RuntimeException e) {
            throw new StandardException("Erro ao adicionar o material ao fornecedor.");
        }
    }
}
