package com.xts.stock.control.dataprovider.costumer.repository;

import com.xts.stock.control.dataprovider.costumer.entity.*;
import com.xts.stock.control.entrypoint.interceptor.exceptions.CustomerAlreadyExistException;
import com.xts.stock.control.entrypoint.interceptor.exceptions.StandardException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CostumerRepository {

    private final CostumerDbRepository costumerDbRepository;

    public void createNewCostumer(final CostumerEntity requestEntity) {

        try {
            costumerDbRepository.findById(requestEntity.getId()).ifPresentOrElse(costumer ->
                    {
                        throw new CustomerAlreadyExistException();
                    },

                    () -> costumerDbRepository.save(requestEntity));

        } catch (final StandardException e) {
            throw new StandardException("Erro ao salvar um novo cliente.");
        }
    }

    public List<CostumerEntity> getAllCostumers() {
        try{
           return costumerDbRepository.findAll();

        } catch (final StandardException e) {
            throw new StandardException("Erro ao buscar todos os clientes.");
        }
    }

    public void updateCostumer(final CostumerUpdateRequestEntity requestEntity) {
        try {
            final CostumerEntity costumerEntityUpdated = costumerDbRepository.findById(requestEntity.getCnpj())
                    .orElseThrow(() ->
                            new StandardException("Erro ao buscar todos os clientes."));

            costumerEntityUpdated.setId(requestEntity.getNewCnpj());
            costumerEntityUpdated.setCostumerName(requestEntity.getNewCostumer());

            costumerDbRepository.deleteById(requestEntity.getCnpj());
            costumerDbRepository.save(costumerEntityUpdated);

        } catch (final StandardException e) {
            throw new StandardException("Erro ao atualizar o cliente.");
        }
    }

    public void updateTag(final TagUpdateRequestEntity requestEntity) {
        final CostumerEntity costumerTagUpdated = costumerDbRepository.findById(requestEntity.getCnpj())
                .orElseThrow(() ->
                        new StandardException("Erro ao buscar todos os clientes."));

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

        } catch (final StandardException e) {
            throw new StandardException("Erro ao atualizar a etiqueta.");
        }
    }

    public void deleteTag(final TagDeleteRequestEntity requestEntity) {
        try {
            final CostumerEntity costumerEntity =
                    costumerDbRepository.findById(requestEntity.getCostumerCnpj())
                    .orElseThrow(() ->
                            new StandardException("Erro ao buscar todos os clientes."));

            costumerEntity.getTagList().removeIf(tag ->
                    requestEntity.getTagCode().equals(tag.getCode()));

            costumerDbRepository.save(costumerEntity);

        } catch (final RuntimeException e) {
            throw new StandardException("Erro ao deletar a etiqueta.");
        }
    }

    public void addTag(final TagAddRequestEntity tagAddRequestEntity) {
        try {
            final CostumerEntity costumerEntity =
                    costumerDbRepository.findById(tagAddRequestEntity.getCostumerCnpj())
                            .orElseThrow(() ->
                                    new StandardException("Erro ao buscar todos os clientes."));

            final TagEntity addTag = TagEntity.builder()
                    .code(tagAddRequestEntity.getCode())
                    .name(tagAddRequestEntity.getName())
                    .build();

            costumerEntity.getTagList().add(addTag);

            costumerDbRepository.save(costumerEntity);

        } catch (final RuntimeException e) {
            throw new StandardException("Erro ao adicionar nova etiqueta para o cliente solicitado.");
        }
    }
}
