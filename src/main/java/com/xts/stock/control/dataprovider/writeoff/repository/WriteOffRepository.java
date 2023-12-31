package com.xts.stock.control.dataprovider.writeoff.repository;

import com.xts.stock.control.dataprovider.writeoff.entity.DeleteWriteOffEntity;
import com.xts.stock.control.dataprovider.writeoff.entity.WriteOffDetailsEntity;
import com.xts.stock.control.dataprovider.writeoff.entity.WriteOffEntity;
import com.xts.stock.control.entrypoint.interceptor.exceptions.StandardException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        } catch (final StandardException e) {
            throw new StandardException("Erro ao salvar saída de etiqueta.");
        }
    }


    public List<WriteOffDetailsEntity> getAllWriteOffs(final String firstDay, final String lastDay) {
        try {
            final Query query = new Query();

            final LocalDate firstDayFormated = LocalDate.parse(firstDay);
            final LocalDate lastDayFormatedd = LocalDate.parse(lastDay).plusDays(1);

            final Criteria dateCriteria = Criteria.where("id")
                    .gte(firstDayFormated.format(DateTimeFormatter.ISO_DATE))
                    .lt(lastDayFormatedd.format(DateTimeFormatter.ISO_DATE));

            query.addCriteria(dateCriteria);

            final List<WriteOffEntity> writeOffEntities = mongoTemplate.find(query, WriteOffEntity.class);

            final List<WriteOffDetailsEntity> allWriteOffDays = new ArrayList<>();

            writeOffEntities.forEach(writeOffEntity ->
                    allWriteOffDays.addAll(writeOffEntity.getWriteOffList()));

            return allWriteOffDays;

        } catch (final StandardException e) {
            throw new StandardException("Erro ao buscar saídas de etiquetas nas datas fornecidas.");
        }
    }

    public void deleteWriteOff(final DeleteWriteOffEntity requestEntity) {
        try {
            final WriteOffEntity writeOffEntity = writeOffDbRepository.findById(requestEntity.getWriteOffDate())
                    .orElseThrow(() ->
                            new StandardException("Erro ao buscar saídas de etiquetas nas datas fornecidas."));

            writeOffEntity.getWriteOffList().removeIf(writeOff ->
                    requestEntity.getWriteOffCode().equals(writeOff.getWriteOffCode()));

            if (writeOffEntity.getWriteOffList().isEmpty()) {
                writeOffDbRepository.deleteById(requestEntity.getWriteOffDate());

            } else {
                writeOffDbRepository.save(writeOffEntity);
            }

        } catch (final StandardException e) {
            throw new StandardException("Erro ao deletar saída de etiqueta.");
        }
    }
}
