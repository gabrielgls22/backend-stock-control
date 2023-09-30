package com.xts.stock.control.dataprovider.supplier.mapper;

import com.xts.stock.control.dataprovider.supplier.entity.MaterialEntity;
import com.xts.stock.control.dataprovider.supplier.entity.SupplierRequestEntity;
import com.xts.stock.control.usecase.supplier.domain.MaterialDomain;
import com.xts.stock.control.usecase.supplier.domain.SupplierRequestDomain;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class SupplierRegisterRepositoryMapperImpl implements SupplierRegisterRepositoryMapper{

    @Override
    public SupplierRequestEntity requestDomainToEntity(final SupplierRequestDomain requestDomain) {
        final SupplierRequestEntity.SupplierRequestEntityBuilder supplierRequestEntityBuilder =
                SupplierRequestEntity.builder();

        if (Objects.nonNull(requestDomain)) {
            supplierRequestEntityBuilder.id(requestDomain.getSupplierCnpj());
            supplierRequestEntityBuilder.supplierName(requestDomain.getSupplierName());
            supplierRequestEntityBuilder.materialList(returnMaterialDomainToEntity(requestDomain.getMaterialList()));
        }

        return supplierRequestEntityBuilder.build();
    }

    protected List<MaterialEntity> returnMaterialDomainToEntity(final List<MaterialDomain> materialDomainList) {
        final List<MaterialEntity> materialEntityList = new ArrayList<>();

        materialDomainList.forEach(material -> {
            final MaterialEntity materialEntity = MaterialEntity.builder()
                    .code(material.getCode())
                    .name(material.getName())
                    .description(material.getDescription())
                    .build();

            materialEntityList.add(materialEntity);
        });

        return materialEntityList;
    }
}
