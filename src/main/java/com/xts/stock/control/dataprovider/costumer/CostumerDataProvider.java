package com.xts.stock.control.dataprovider.costumer;

import com.xts.stock.control.dataprovider.costumer.entity.*;
import com.xts.stock.control.dataprovider.costumer.mapper.CostumerRepositoryMapper;
import com.xts.stock.control.dataprovider.costumer.repository.CostumerRepository;
import com.xts.stock.control.usecase.costumer.gateway.CostumerGateway;
import com.xts.stock.control.usecase.costumer.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CostumerDataProvider implements CostumerGateway {

    private final CostumerRepositoryMapper costumerRepositoryMapper;

    private final CostumerRepository costumerRepository;

    @Override
    public void createNewCostumer(final CostumerDomain requestDomain) {

        final CostumerEntity requestEntity =
                costumerRepositoryMapper.createCostumerRequestDomainToEntity(requestDomain);

        costumerRepository.createNewCostumer(requestEntity);

    }

    @Override
    public List<CostumerDomain> getAllCostumers() {
        final List<CostumerEntity> responseEntity = costumerRepository.getAllCostumers();

        return costumerRepositoryMapper.getAllCostumersEntityToDomain(responseEntity);

    }

    @Override
    public void updateCostumer(final CostumerUpdateRequestDomain requestDomain) {
        final CostumerUpdateRequestEntity requestEntity =
                costumerRepositoryMapper.updateCostumerRequestDomainToEntity(requestDomain);

        costumerRepository.updateCostumer(requestEntity);
    }

    @Override
    public void updateTag(final TagUpdateRequestDomain requestDomain) {
        final TagUpdateRequestEntity requestEntity =
                costumerRepositoryMapper.updateTagRequestDomainToEntity(requestDomain);

        costumerRepository.updateTag(requestEntity);
    }

    @Override
    public void deleteCostumer(final String costumerCnpj) {

        costumerRepository.deleteCostumer(costumerCnpj);
    }

    @Override
    public void deleteTag(TagDeleteRequestDomain requestDomain) {
        final TagDeleteRequestEntity requestEntity =
                costumerRepositoryMapper.deleteTagRequestDomainToEntity(requestDomain);

        costumerRepository.deleteTag(requestEntity);
    }

    @Override
    public void addTag(final TagAddRequestDomain requestDomain) {
        final TagAddRequestEntity requestEntity =
                costumerRepositoryMapper.addTagRequestDomainToEntity(requestDomain);

        costumerRepository.addTag(requestEntity);
    }
}
