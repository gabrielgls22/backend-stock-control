package com.xts.stock.control.dataprovider.writeoff.repository;

import com.xts.stock.control.dataprovider.writeoff.entity.WriteOffEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WriteOffDbRepository extends MongoRepository<WriteOffEntity, String> {

    Optional<WriteOffEntity> findByWriteOffListServiceOrder(String serviceOrder);
}
