package com.xts.stock.control.dataprovider.writeoff.repository;

import com.xts.stock.control.dataprovider.writeoff.entity.DeleteWriteOffEntity;
import com.xts.stock.control.dataprovider.writeoff.entity.WriteOffDetailsEntity;
import com.xts.stock.control.dataprovider.writeoff.entity.WriteOffEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<WriteOffDetailsEntity> getAllWriteOffs(final String writeOffDate) {
        try{

            return writeOffDbRepository
                    .findById(writeOffDate)
                    .map(WriteOffEntity::getWriteOffList)
                    .orElse(Collections.emptyList());

        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to get day write-off in db, with log: " + e.getMessage());
        }
    }

    public void deleteWriteOff(final DeleteWriteOffEntity requestEntity) {
        try {
            final WriteOffEntity writeOffEntity = writeOffDbRepository.findById(requestEntity.getWriteOffDate())
                    .orElseThrow(() -> new RuntimeException("Error trying to get write-off in db for date: " +
                            requestEntity.getWriteOffDate()));

            writeOffEntity.getWriteOffList().removeIf(writeOff ->
                    requestEntity.getWriteOffCode().equals(writeOff.getWriteOffCode()));

            writeOffDbRepository.save(writeOffEntity);

        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to delete write-off in db, with log: " + e.getMessage());
        }
    }
}
