package com.xts.stock.control.usecase.supplier;

import com.xts.stock.control.usecase.supplier.domain.SupplierDomain;
import com.xts.stock.control.usecase.supplier.gateway.SupplierGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierRegisterUseCase {

    private final SupplierGateway supplierGateway;

    public void execute(final SupplierDomain requestDomain) {

        supplierGateway.createNewSupplier(requestDomain);
    }
}
