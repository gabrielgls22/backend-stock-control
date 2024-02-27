package com.xts.stock.control.dataprovider.supplier;

import com.xts.stock.control.dataprovider.supplier.entity.*;
import com.xts.stock.control.dataprovider.supplier.mapper.SupplierRepositoryMapper;
import com.xts.stock.control.dataprovider.supplier.repository.SupplierRepository;
import com.xts.stock.control.usecase.supplier.domain.*;
import com.xts.stock.control.usecase.supplier.gateway.SupplierGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SupplierDataProvider implements SupplierGateway {

    private final SupplierRepositoryMapper supplierRepositoryMapper;

    private final SupplierRepository supplierRepository;

    @Override
    public void createNewSupplier(final SupplierDomain requestDomain) {

        final SupplierEntity requestEntity =
                supplierRepositoryMapper.createSupplierRequestDomainToEntity(requestDomain);

        supplierRepository.createNewSupplier(requestEntity);

    }

    @Override
    public List<SupplierDomain> getAllSuppliers() {
        final List<SupplierEntity> responseEntity = supplierRepository.getAllSuppliers();

        final List<SupplierDomain> allSuppliersDomain =
                supplierRepositoryMapper.getAllSuppliersEntityToDomain(responseEntity);

        allSuppliersDomain.sort(Comparator.comparing(SupplierDomain::getSupplierName));

        return allSuppliersDomain;

    }

    @Override
    public void updateSupplier(final SupplierUpdateRequestDomain requestDomain) {
        final SupplierUpdateRequestEntity requestEntity =
                supplierRepositoryMapper.updateSupplierRequestDomainToEntity(requestDomain);

        supplierRepository.updateSupplier(requestEntity);
    }

    @Override
    public void updateMaterial(final MaterialUpdateRequestDomain requestDomain) {
        final MaterialUpdateRequestEntity requestEntity =
                supplierRepositoryMapper.updateMaterialRequestDomainToEntity(requestDomain);

        supplierRepository.updateMaterial(requestEntity);
    }

    @Override
    public void deleteSupplier(final String supplierCnpj) {

        supplierRepository.deleteSupplier(supplierCnpj);
    }

    @Override
    public void deleteMaterial(MaterialDeleteRequestDomain requestDomain) {
        final MaterialDeleteRequestEntity requestEntity =
                supplierRepositoryMapper.deleteMaterialRequestDomainToEntity(requestDomain);

        supplierRepository.deleteMaterial(requestEntity);
    }

    @Override
    public void addMaterial(final MaterialAddRequestDomain requestDomain) {
        final MaterialAddRequestEntity requestEntity =
                supplierRepositoryMapper.addMaterialRequestDomainToEntity(requestDomain);

        supplierRepository.addMaterial(requestEntity);
    }
}
