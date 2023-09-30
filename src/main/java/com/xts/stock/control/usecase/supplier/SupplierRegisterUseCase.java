package com.xts.stock.control.usecase.supplier;

import com.xts.stock.control.usecase.supplier.domain.SupplierRequestDomain;
import com.xts.stock.control.usecase.supplier.gateway.SupplierGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierRegisterUseCase {

    private final SupplierGateway supplierGateway;

    public void execute(final SupplierRequestDomain requestDomain) {

        supplierGateway.createNewSupplier(requestDomain);
    }
}
