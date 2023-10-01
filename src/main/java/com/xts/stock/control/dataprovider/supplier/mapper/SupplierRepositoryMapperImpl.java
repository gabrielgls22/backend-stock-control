package com.xts.stock.control.dataprovider.supplier.mapper;

import com.xts.stock.control.dataprovider.supplier.entity.*;
import com.xts.stock.control.usecase.supplier.domain.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class SupplierRepositoryMapperImpl implements SupplierRepositoryMapper {

    @Override
    public SupplierEntity createSupplierRequestDomainToEntity(final SupplierDomain requestDomain) {
        final SupplierEntity.SupplierEntityBuilder supplierRequestEntityBuilder =
                SupplierEntity.builder();

        if (Objects.nonNull(requestDomain)) {
            supplierRequestEntityBuilder.id(requestDomain.getSupplierCnpj());
            supplierRequestEntityBuilder.supplierName(requestDomain.getSupplierName());
            supplierRequestEntityBuilder.materialList(returnMaterialDomainToEntity(requestDomain.getMaterialList()));
        }

        return supplierRequestEntityBuilder.build();
    }

    @Override
    public SupplierUpdateRequestEntity updateSupplierRequestDomainToEntity(
            final SupplierUpdateRequestDomain requestDomain) {
        final SupplierUpdateRequestEntity.SupplierUpdateRequestEntityBuilder supplierUpdateRequestEntityBuilder =
                SupplierUpdateRequestEntity.builder();

        if (Objects.nonNull(requestDomain)) {
            supplierUpdateRequestEntityBuilder.cnpj(requestDomain.getCnpj());
            supplierUpdateRequestEntityBuilder.newCnpj(requestDomain.getNewCnpj());
            supplierUpdateRequestEntityBuilder.newSupplier(requestDomain.getNewSupplier());
        }

        return supplierUpdateRequestEntityBuilder.build();
    }

    @Override
    public MaterialUpdateRequestEntity updateMaterialRequestDomainToEntity(
            final MaterialUpdateRequestDomain requestDomain) {
        final MaterialUpdateRequestEntity.MaterialUpdateRequestEntityBuilder materialUpdateRequestEntityBuilder =
                MaterialUpdateRequestEntity.builder();

        if (Objects.nonNull(requestDomain)) {
            materialUpdateRequestEntityBuilder.cnpj(requestDomain.getCnpj());
            materialUpdateRequestEntityBuilder.code(requestDomain.getCode());
            materialUpdateRequestEntityBuilder.name(requestDomain.getName());
            materialUpdateRequestEntityBuilder.description(requestDomain.getDescription());
        }

        return materialUpdateRequestEntityBuilder.build();
    }

    @Override
    public MaterialDeleteRequestEntity deleteMaterialRequestDomainToEntity(
            final MaterialDeleteRequestDomain requestDomain) {
        final MaterialDeleteRequestEntity.MaterialDeleteRequestEntityBuilder materialDeleteRequestEntityBuilder =
                MaterialDeleteRequestEntity.builder();

        if (Objects.nonNull(requestDomain)) {
            materialDeleteRequestEntityBuilder.supplierCnpj(requestDomain.getSupplierCnpj());
            materialDeleteRequestEntityBuilder.materialCode(requestDomain.getMaterialCode());
        }

        return materialDeleteRequestEntityBuilder.build();
    }

    @Override
    public MaterialAddRequestEntity addMaterialRequestDomainToEntity(final MaterialAddRequestDomain requestDomain) {
        final MaterialAddRequestEntity.MaterialAddRequestEntityBuilder materialAddRequestEntityBuilder =
                MaterialAddRequestEntity.builder();

        if (Objects.nonNull(requestDomain)) {
            materialAddRequestEntityBuilder.supplierCnpj(requestDomain.getSupplierCnpj());
            materialAddRequestEntityBuilder.code(requestDomain.getCode());
            materialAddRequestEntityBuilder.name(requestDomain.getName());
            materialAddRequestEntityBuilder.description(requestDomain.getDescription());
        }

        return materialAddRequestEntityBuilder.build();
    }

    @Override
    public List<SupplierDomain> getAllSuppliersEntityToDomain(final List<SupplierEntity> responseEntity) {
        final List<SupplierDomain> supplierDomainList = new ArrayList<>();

        responseEntity.forEach(supplier -> {
            final SupplierDomain supplierEntity = SupplierDomain.builder()
                    .supplierCnpj(supplier.getId())
                    .supplierName(supplier.getSupplierName())
                    .materialList(returnMaterialEntityToDomain(supplier.getMaterialList()))
                    .build();

            supplierDomainList.add(supplierEntity);
        });

        return supplierDomainList;
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

    protected List<MaterialDomain> returnMaterialEntityToDomain(final List<MaterialEntity> materialEntityList) {
        final List<MaterialDomain> materialDomainList = new ArrayList<>();

        materialEntityList.forEach(material -> {
            final MaterialDomain materialDomain = MaterialDomain.builder()
                    .code(material.getCode())
                    .name(material.getName())
                    .description(material.getDescription())
                    .build();

            materialDomainList.add(materialDomain);
        });

        return materialDomainList;
    }

}
