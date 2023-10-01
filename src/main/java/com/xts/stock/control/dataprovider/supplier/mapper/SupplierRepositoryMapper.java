package com.xts.stock.control.dataprovider.supplier.mapper;

import com.xts.stock.control.dataprovider.supplier.entity.*;
import com.xts.stock.control.usecase.supplier.domain.*;

import java.util.List;

public interface SupplierRepositoryMapper {

    SupplierEntity createSupplierRequestDomainToEntity(SupplierDomain requestDomain);

    SupplierUpdateRequestEntity updateSupplierRequestDomainToEntity(SupplierUpdateRequestDomain requestDomain);

    MaterialUpdateRequestEntity updateMaterialRequestDomainToEntity(MaterialUpdateRequestDomain requestDomain);

    MaterialDeleteRequestEntity deleteMaterialRequestDomainToEntity(MaterialDeleteRequestDomain requestDomain);

    MaterialAddRequestEntity addMaterialRequestDomainToEntity(MaterialAddRequestDomain requestDomain);

    List<SupplierDomain> getAllSuppliersEntityToDomain(List<SupplierEntity> responseEntity);
}
