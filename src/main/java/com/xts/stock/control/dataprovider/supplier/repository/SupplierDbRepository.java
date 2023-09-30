package com.xts.stock.control.dataprovider.supplier.repository;

import com.xts.stock.control.dataprovider.supplier.entity.SupplierRequestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierDbRepository extends MongoRepository<SupplierRequestEntity, String> {
}
