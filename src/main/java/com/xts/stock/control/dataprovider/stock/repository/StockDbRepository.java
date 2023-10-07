package com.xts.stock.control.dataprovider.stock.repository;

import com.xts.stock.control.dataprovider.stock.entity.StockEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockDbRepository extends MongoRepository<StockEntity, String> {
}
