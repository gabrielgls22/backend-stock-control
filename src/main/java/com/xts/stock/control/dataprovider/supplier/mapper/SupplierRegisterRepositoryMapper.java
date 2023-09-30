package com.xts.stock.control.dataprovider.supplier.mapper;

import com.xts.stock.control.dataprovider.supplier.entity.SupplierRequestEntity;
import com.xts.stock.control.usecase.supplier.domain.SupplierRequestDomain;

public interface SupplierRegisterRepositoryMapper {

    SupplierRequestEntity requestDomainToEntity(SupplierRequestDomain requestDomain);
}
