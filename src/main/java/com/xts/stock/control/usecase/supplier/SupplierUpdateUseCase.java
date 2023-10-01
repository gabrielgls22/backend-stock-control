package com.xts.stock.control.usecase.supplier;

import com.xts.stock.control.usecase.supplier.domain.SupplierUpdateRequestDomain;
import com.xts.stock.control.usecase.supplier.gateway.SupplierGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierUpdateUseCase {

    private final SupplierGateway supplierGateway;

    public void execute(final SupplierUpdateRequestDomain requestDomain) {
        supplierGateway.updateSupplier(requestDomain);
    }
}
