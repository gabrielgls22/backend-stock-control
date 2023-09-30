package com.xts.stock.control.dataprovider.supplier.repository;

import com.xts.stock.control.dataprovider.supplier.entity.SupplierRequestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplierRepository {

    private final SupplierDbRepository supplierDbRepository;

    public void createNewSupplier(final SupplierRequestEntity requestEntity) {
        try{
            supplierDbRepository.save(requestEntity);

        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to save new supplier in db");
        }

    }
}
