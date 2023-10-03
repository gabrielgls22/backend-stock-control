package com.xts.stock.control.dataprovider.costumer.mapper;

import com.xts.stock.control.dataprovider.costumer.entity.*;
import com.xts.stock.control.dataprovider.costumer.entity.*;
import com.xts.stock.control.usecase.costumer.domain.*;
import com.xts.stock.control.usecase.costumer.domain.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CostumerRepositoryMapperImpl implements CostumerRepositoryMapper {


    @Override
    public CostumerEntity createCostumerRequestDomainToEntity(final CostumerDomain requestDomain) {
        final CostumerEntity.CostumerEntityBuilder costumerRequestEntityBuilder =
                CostumerEntity.builder();

        if (Objects.nonNull(requestDomain)) {
            costumerRequestEntityBuilder.id(requestDomain.getCostumerCnpj());
            costumerRequestEntityBuilder.costumerName(requestDomain.getCostumerName());
            costumerRequestEntityBuilder.tagList(returnTagDomainToEntity(requestDomain.getTagList()));
        }

        return costumerRequestEntityBuilder.build();
    }

    @Override
    public CostumerUpdateRequestEntity updateCostumerRequestDomainToEntity(
            final CostumerUpdateRequestDomain requestDomain) {
        final CostumerUpdateRequestEntity.CostumerUpdateRequestEntityBuilder costumerUpdateRequestEntityBuilder =
                CostumerUpdateRequestEntity.builder();

        if (Objects.nonNull(requestDomain)) {
            costumerUpdateRequestEntityBuilder.cnpj(requestDomain.getCnpj());
            costumerUpdateRequestEntityBuilder.newCnpj(requestDomain.getNewCnpj());
            costumerUpdateRequestEntityBuilder.newCostumer(requestDomain.getNewCostumer());
        }

        return costumerUpdateRequestEntityBuilder.build();
    }

    @Override
    public TagUpdateRequestEntity updateTagRequestDomainToEntity(
            final TagUpdateRequestDomain requestDomain) {
        final TagUpdateRequestEntity.TagUpdateRequestEntityBuilder tagUpdateRequestEntityBuilder =
                TagUpdateRequestEntity.builder();

        if (Objects.nonNull(requestDomain)) {
            tagUpdateRequestEntityBuilder.cnpj(requestDomain.getCnpj());
            tagUpdateRequestEntityBuilder.code(requestDomain.getCode());
            tagUpdateRequestEntityBuilder.name(requestDomain.getName());
        }

        return tagUpdateRequestEntityBuilder.build();
    }

    @Override
    public TagDeleteRequestEntity deleteTagRequestDomainToEntity(
            final TagDeleteRequestDomain requestDomain) {
        final TagDeleteRequestEntity.TagDeleteRequestEntityBuilder tagDeleteRequestEntityBuilder =
                TagDeleteRequestEntity.builder();

        if (Objects.nonNull(requestDomain)) {
            tagDeleteRequestEntityBuilder.costumerCnpj(requestDomain.getCostumerCnpj());
            tagDeleteRequestEntityBuilder.tagCode(requestDomain.getTagCode());
        }

        return tagDeleteRequestEntityBuilder.build();
    }

    @Override
    public TagAddRequestEntity addTagRequestDomainToEntity(final TagAddRequestDomain requestDomain) {
        final TagAddRequestEntity.TagAddRequestEntityBuilder tagAddRequestEntityBuilder =
                TagAddRequestEntity.builder();

        if (Objects.nonNull(requestDomain)) {
            tagAddRequestEntityBuilder.costumerCnpj(requestDomain.getCostumerCnpj());
            tagAddRequestEntityBuilder.code(requestDomain.getCode());
            tagAddRequestEntityBuilder.name(requestDomain.getName());
        }

        return tagAddRequestEntityBuilder.build();
    }

    @Override
    public List<CostumerDomain> getAllCostumersEntityToDomain(final List<CostumerEntity> responseEntity) {
        final List<CostumerDomain> costumerDomainList = new ArrayList<>();

        responseEntity.forEach(costumer -> {
            final CostumerDomain costumerEntity = CostumerDomain.builder()
                    .costumerCnpj(costumer.getId())
                    .costumerName(costumer.getCostumerName())
                    .tagList(returnTagEntityToDomain(costumer.getTagList()))
                    .build();

            costumerDomainList.add(costumerEntity);
        });

        return costumerDomainList;
    }

    protected List<TagEntity> returnTagDomainToEntity(final List<TagDomain> tagDomainList) {
        final List<TagEntity> tagEntityList = new ArrayList<>();

        tagDomainList.forEach(tag -> {
            final TagEntity tagEntity = TagEntity.builder()
                    .code(tag.getCode())
                    .name(tag.getName())
                    .build();

            tagEntityList.add(tagEntity);
        });

        return tagEntityList;
    }

    protected List<TagDomain> returnTagEntityToDomain(final List<TagEntity> tagEntityList) {
        final List<TagDomain> tagDomainList = new ArrayList<>();

        tagEntityList.forEach(tag -> {
            final TagDomain tagDomain = TagDomain.builder()
                    .code(tag.getCode())
                    .name(tag.getName())
                    .build();

            tagDomainList.add(tagDomain);
        });

        return tagDomainList;
    }
}
