package com.xts.stock.control.dataprovider.supplier.repository;

import com.xts.stock.control.dataprovider.supplier.entity.SupplierEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierDbRepository extends MongoRepository<SupplierEntity, String> {
}
