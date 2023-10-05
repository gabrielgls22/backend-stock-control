package com.xts.stock.control.dataprovider.writeoff.mapper;

import com.xts.stock.control.dataprovider.writeoff.entity.WriteOffDetailsEntity;
import com.xts.stock.control.dataprovider.writeoff.entity.WriteOffEntity;
import com.xts.stock.control.dataprovider.writeoff.entity.WriteOffMaterialEntity;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffMaterialsDomain;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    protected List<WriteOffDetailsEntity> returnWriteOffListDomainToEntity(final WriteOffDomain requestDomain) {
        final List<WriteOffDetailsEntity> writeOffDetailsEntityList = new ArrayList<>();

        final WriteOffDetailsEntity writeOffDetailsEntity = WriteOffDetailsEntity.builder()
                .writeOffCode(requestDomain.getWriteOffCode())
                .costumerCnpj(requestDomain.getCostumerCnpj())
                .costumerName(requestDomain.getCostumerName())
                .tagCode(requestDomain.getTagCode())
                .tagName(requestDomain.getTagName())
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
                    .description(material.getDescription())
                    .build();

            materialsEntityList.add(materialEntity);
        });

        return materialsEntityList;
    }
}
