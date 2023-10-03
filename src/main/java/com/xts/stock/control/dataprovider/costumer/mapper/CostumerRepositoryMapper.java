package com.xts.stock.control.dataprovider.costumer.mapper;

import com.xts.stock.control.dataprovider.costumer.entity.*;
import com.xts.stock.control.usecase.costumer.domain.*;

import java.util.List;

public interface CostumerRepositoryMapper {

    CostumerEntity createCostumerRequestDomainToEntity(CostumerDomain requestDomain);

    CostumerUpdateRequestEntity updateCostumerRequestDomainToEntity(CostumerUpdateRequestDomain requestDomain);

    TagUpdateRequestEntity updateTagRequestDomainToEntity(TagUpdateRequestDomain requestDomain);

    TagDeleteRequestEntity deleteTagRequestDomainToEntity(TagDeleteRequestDomain requestDomain);

    TagAddRequestEntity addTagRequestDomainToEntity(TagAddRequestDomain requestDomain);

    List<CostumerDomain> getAllCostumersEntityToDomain(List<CostumerEntity> responseEntity);
}
