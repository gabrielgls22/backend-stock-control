package com.xts.stock.control.dataprovider.writeoff.mapper;

import com.xts.stock.control.dataprovider.writeoff.entity.*;
import com.xts.stock.control.usecase.writeoff.domain.DeleteWriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.UpdateWriteOffRequestDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffMaterialsDomain;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class WriteOffRepositoryMapperImpl implements WriteOffRepositoryMapper{

    @Override
    public WriteOffEntity registerWriteOffDomainToEntity(final WriteOffDomain requestDomain) {
        final WriteOffEntity.WriteOffEntityBuilder writeOffEntityBuilder =
                WriteOffEntity.builder();

        if (Objects.nonNull(requestDomain)) {
            writeOffEntityBuilder.id(requestDomain.getWriteOffDate());
            writeOffEntityBuilder.writeOffList(returnWriteOffListDomainToEntity(requestDomain));
        }

        return writeOffEntityBuilder.build();
    }

    @Override
    public List<WriteOffDomain> getWriteOffsEntityToDomain(final List<WriteOffDetailsEntity> responseEntity) {
        final List<WriteOffDomain> writeOffDomainList = new ArrayList<>();

        responseEntity.forEach(writeOffEntity -> {
            final WriteOffDomain writeOffDomain = WriteOffDomain.builder()
                    .writeOffDate(writeOffEntity.getWriteOffDate())
                    .writeOffCode(writeOffEntity.getWriteOffCode())
                    .costumerCnpj(writeOffEntity.getCostumerCnpj())
                    .costumerName(writeOffEntity.getCostumerName())
                    .tagCode(writeOffEntity.getTagCode())
                    .tagName(writeOffEntity.getTagName())
                    .tagQuantity(writeOffEntity.getTagQuantity())
                    .serviceOrder(writeOffEntity.getServiceOrder())
                    .materials(responseWriteOffMaterialsEntityToDomain(writeOffEntity.getMaterials()))
                    .build();

            writeOffDomainList.add(writeOffDomain);
        });

        return writeOffDomainList;
    }

    @Override
    public DeleteWriteOffEntity deleteWriteOffDomainToEntity(final DeleteWriteOffDomain requestDomain) {
        final DeleteWriteOffEntity.DeleteWriteOffEntityBuilder deleteWriteOffEntityBuilder =
                DeleteWriteOffEntity.builder();

        if (Objects.nonNull(requestDomain)) {
            deleteWriteOffEntityBuilder.writeOffDate(requestDomain.getWriteOffDate());
            deleteWriteOffEntityBuilder.writeOffCode(requestDomain.getWriteOffCode());
        }

        return deleteWriteOffEntityBuilder.build();
    }

    @Override
    public List<WriteOffDomain> writeOffByServiceOrderEntityToDomain(
            final List<WriteOffDetailsEntity> responseEntity) {

        final List<WriteOffDomain> writeOffDomainList = new ArrayList<>();

        responseEntity.forEach(writeOffEntity -> {
            final WriteOffDomain writeOffDomain = WriteOffDomain.builder()
                    .writeOffDate(writeOffEntity.getWriteOffDate())
                    .writeOffCode(writeOffEntity.getWriteOffCode())
                    .costumerCnpj(writeOffEntity.getCostumerCnpj())
                    .costumerName(writeOffEntity.getCostumerName())
                    .tagCode(writeOffEntity.getTagCode())
                    .tagName(writeOffEntity.getTagName())
                    .tagQuantity(writeOffEntity.getTagQuantity())
                    .serviceOrder(writeOffEntity.getServiceOrder())
                    .materials(responseWriteOffMaterialsEntityToDomain(writeOffEntity.getMaterials()))
                    .build();

            writeOffDomainList.add(writeOffDomain);
        });

        return writeOffDomainList;
    }

    @Override
    public UpdateWriteOffRequestEntity updateWriteOffRequestDomainToEntity(
            final UpdateWriteOffRequestDomain requestDomain) {

        return Optional.ofNullable(requestDomain)
                .map(writeOffDomain -> UpdateWriteOffRequestEntity.builder()
                        .writeOffDate(writeOffDomain.getWriteOffDate())
                        .writeOffCode(writeOffDomain.getWriteOffCode())
                        .costumerName(writeOffDomain.getCostumerName())
                        .costumerCnpj(writeOffDomain.getCostumerCnpj())
                        .tagName(writeOffDomain.getTagName())
                        .tagCode(writeOffDomain.getTagCode())
                        .tagQuantity(writeOffDomain.getTagQuantity())
                        .serviceOrder(writeOffDomain.getServiceOrder())
                        .firstDay(writeOffDomain.getFirstDay())
                        .lastDay(writeOffDomain.getLastDay())
                        .build())
                .orElse(UpdateWriteOffRequestEntity.builder().build());
    }

    protected List<WriteOffDetailsEntity> returnWriteOffListDomainToEntity(final WriteOffDomain requestDomain) {
        final List<WriteOffDetailsEntity> writeOffDetailsEntityList = new ArrayList<>();

        final WriteOffDetailsEntity writeOffDetailsEntity = WriteOffDetailsEntity.builder()
                .writeOffDate(requestDomain.getWriteOffDate())
                .writeOffCode(requestDomain.getWriteOffCode())
                .costumerCnpj(requestDomain.getCostumerCnpj())
                .costumerName(requestDomain.getCostumerName())
                .tagCode(requestDomain.getTagCode())
                .tagName(requestDomain.getTagName())
                .tagQuantity(requestDomain.getTagQuantity())
                .serviceOrder(requestDomain.getServiceOrder())
                .materials(returnMaterialsDomainToEntity(requestDomain.getMaterials()))
                .build();

        writeOffDetailsEntityList.add(writeOffDetailsEntity);

        return writeOffDetailsEntityList;
    }

    protected List<WriteOffMaterialEntity> returnMaterialsDomainToEntity(
            final List<WriteOffMaterialsDomain> materialsDomainList) {
        final List<WriteOffMaterialEntity> materialsEntityList = new ArrayList<>();

        materialsDomainList.forEach(material -> {
            final WriteOffMaterialEntity materialEntity = WriteOffMaterialEntity.builder()
                    .barCode(material.getBarCode())
                    .lengthUsed(material.getLengthUsed())
                    .name(material.getName())
                    .supplier(material.getSupplier())
                    .width(material.getWidth())
                    .length(material.getLength())
                    .batch(material.getBatch())
                    .build();

            materialsEntityList.add(materialEntity);
        });

        return materialsEntityList;
    }

    protected List<WriteOffMaterialsDomain> responseWriteOffMaterialsEntityToDomain(
            final List<WriteOffMaterialEntity> writeOffMaterialEntityList) {
        final List<WriteOffMaterialsDomain> writeOffMaterialsDomainList = new ArrayList<>();

        writeOffMaterialEntityList.forEach(materialEntity -> {
            final WriteOffMaterialsDomain materialsDomain = WriteOffMaterialsDomain.builder()
                    .barCode(materialEntity.getBarCode())
                    .lengthUsed(materialEntity.getLengthUsed())
                    .name(materialEntity.getName())
                    .supplier(materialEntity.getSupplier())
                    .width(materialEntity.getWidth())
                    .length(materialEntity.getLength())
                    .batch(materialEntity.getBatch())
                    .build();

            writeOffMaterialsDomainList.add(materialsDomain);
        });

        return writeOffMaterialsDomainList;
    }
}
