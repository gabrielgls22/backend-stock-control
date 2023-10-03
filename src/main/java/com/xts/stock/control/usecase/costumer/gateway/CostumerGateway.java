package com.xts.stock.control.usecase.costumer.gateway;

import com.xts.stock.control.usecase.costumer.domain.*;

import java.util.List;

public interface CostumerGateway {

    void createNewCostumer(CostumerDomain requestDomain);

    List<CostumerDomain> getAllCostumers();

    void updateCostumer(CostumerUpdateRequestDomain requestDomain);

    void updateTag(TagUpdateRequestDomain requestDomain);

    void deleteCostumer(String costumerCnpj);

    void deleteTag(TagDeleteRequestDomain requestDomain);

    void addTag(TagAddRequestDomain requestDomain);
}
