package com.xts.stock.control.dataprovider.supplier;

import com.xts.stock.control.dataprovider.supplier.entity.SupplierRequestEntity;
import com.xts.stock.control.dataprovider.supplier.mapper.SupplierRegisterRepositoryMapper;
import com.xts.stock.control.dataprovider.supplier.repository.SupplierRepository;
import com.xts.stock.control.usecase.supplier.domain.SupplierRequestDomain;
import com.xts.stock.control.usecase.supplier.gateway.SupplierGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplierDataProvider implements SupplierGateway {

    private final SupplierRegisterRepositoryMapper supplierRegisterRepositoryMapper;

    private final SupplierRepository supplierRepository;

    @Override
    public void createNewSupplier(final SupplierRequestDomain requestDomain) {

        final SupplierRequestEntity requestEntity =
                supplierRegisterRepositoryMapper.requestDomainToEntity(requestDomain);

        supplierRepository.createNewSupplier(requestEntity);

    }
}
