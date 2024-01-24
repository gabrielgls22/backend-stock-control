package com.xts.stock.control.dataprovider.auth.repository;

import com.xts.stock.control.dataprovider.auth.entity.UserAuthEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationDbRepository extends MongoRepository<UserAuthEntity, String> {
}
