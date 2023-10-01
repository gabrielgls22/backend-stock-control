package com.xts.stock.control.usecase.supplier.gateway;

import com.xts.stock.control.usecase.supplier.domain.*;

import java.util.List;

public interface SupplierGateway {

    void createNewSupplier(SupplierDomain requestDomain);

    List<SupplierDomain> getAllSuppliers();

    void updateSupplier(SupplierUpdateRequestDomain requestDomain);

    void updateMaterial(MaterialUpdateRequestDomain requestDomain);

    void deleteSupplier(String supplierCnpj);

    void deleteMaterial(MaterialDeleteRequestDomain requestDomain);

    void addMaterial(MaterialAddRequestDomain requestDomain);
}
