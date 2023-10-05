package com.xts.stock.control.dataprovider.writeoff.repository;

import com.xts.stock.control.dataprovider.writeoff.entity.WriteOffEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WriteOffRepository {

    private final WriteOffDbRepository writeOffDbRepository;

    private final MongoTemplate mongoTemplate;

    public void registerWriteOff(final WriteOffEntity requestEntity) {
        try {

            writeOffDbRepository.findById(requestEntity.getId())
                    .ifPresentOrElse(responseWriteOff -> {
                        final Query query = new Query(Criteria.where("_id").is(requestEntity.getId()));

                        final Update update = new Update().push("writeOffList", requestEntity.getWriteOffList().get(0));

                        mongoTemplate.updateFirst(query, update, WriteOffEntity.class);

                    }, () -> writeOffDbRepository.save(requestEntity));

        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to save new write-off in db, with log: " + e.getMessage());
        }
    }
}
