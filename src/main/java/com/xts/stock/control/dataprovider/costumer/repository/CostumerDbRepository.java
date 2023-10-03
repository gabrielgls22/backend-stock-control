package com.xts.stock.control.dataprovider.costumer.repository;

import com.xts.stock.control.dataprovider.costumer.entity.CostumerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumerDbRepository extends MongoRepository<CostumerEntity, String> {
}
