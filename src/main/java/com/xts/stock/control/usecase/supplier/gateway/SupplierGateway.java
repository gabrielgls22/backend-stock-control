package com.xts.stock.control.usecase.supplier.gateway;

import com.xts.stock.control.usecase.supplier.domain.SupplierRequestDomain;

public interface SupplierGateway {

    void createNewSupplier(SupplierRequestDomain requestDomain);
}
