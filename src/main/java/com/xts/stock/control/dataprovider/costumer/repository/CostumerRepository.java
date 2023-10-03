package com.xts.stock.control.dataprovider.costumer.repository;

import com.xts.stock.control.dataprovider.costumer.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CostumerRepository {

    private final CostumerDbRepository costumerDbRepository;

    public void createNewCostumer(final CostumerEntity requestEntity) {

        try{
            costumerDbRepository.save(requestEntity);

        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to save new costumer in db, with log: " + e.getMessage());
        }

    }

    public List<CostumerEntity> getAllCostumers() {
        try{
           return costumerDbRepository.findAll();

        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to save new costumer in db, with log: " + e.getMessage());
        }
    }

    public void updateCostumer(final CostumerUpdateRequestEntity requestEntity) {
        try {
            final CostumerEntity costumerEntityUpdated = costumerDbRepository.findById(requestEntity.getCnpj())
                    .orElseThrow(() -> new RuntimeException("Error trying to get costumer in db, with log: "));

            costumerEntityUpdated.setId(requestEntity.getNewCnpj());
            costumerEntityUpdated.setCostumerName(requestEntity.getNewCostumer());

            costumerDbRepository.deleteById(requestEntity.getCnpj());
            costumerDbRepository.save(costumerEntityUpdated);

        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to update costumer in db, with log: " + e.getMessage());
        }
    }

    public void updateTag(final TagUpdateRequestEntity requestEntity) {
        final CostumerEntity costumerTagUpdated = costumerDbRepository.findById(requestEntity.getCnpj())
                .orElseThrow(() -> new RuntimeException("Error trying to get costumer in db, with log: "));

        costumerTagUpdated.getTagList().forEach(tag -> {
            if (requestEntity.getCode().equals(tag.getCode())) {
                tag.setName(requestEntity.getName());
            }
        });

        costumerDbRepository.save(costumerTagUpdated);
    }

    public void deleteCostumer(final String costumerCnpj) {
        try {
            costumerDbRepository.deleteById(costumerCnpj);

        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to delete costumer in db, with log: " + e.getMessage());
        }
    }

    public void deleteTag(final TagDeleteRequestEntity requestEntity) {
        try {
            final CostumerEntity costumerEntity =
                    costumerDbRepository.findById(requestEntity.getCostumerCnpj())
                    .orElseThrow(() -> new RuntimeException("Error trying to get costumer in db, with log: "));

            costumerEntity.getTagList().removeIf(tag ->
                    requestEntity.getTagCode().equals(tag.getCode()));

            costumerDbRepository.save(costumerEntity);

        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to delete tag in db, with log: " + e.getMessage());
        }
    }

    public void addTag(final TagAddRequestEntity tagAddRequestEntity) {
        try {
            final CostumerEntity costumerEntity =
                    costumerDbRepository.findById(tagAddRequestEntity.getCostumerCnpj())
                            .orElseThrow(() -> new RuntimeException("Error trying to get costumer in db, with log: "));

            final TagEntity addTag = TagEntity.builder()
                    .code(tagAddRequestEntity.getCode())
                    .name(tagAddRequestEntity.getName())
                    .build();

            costumerEntity.getTagList().add(addTag);

            costumerDbRepository.save(costumerEntity);

        } catch (final RuntimeException e) {
            throw new RuntimeException("Error trying to add tag to a costumer in db, with log: " + e.getMessage());
        }
    }
}
