package com.xts.stock.control.usecase.supplier;

import com.xts.stock.control.usecase.supplier.gateway.SupplierGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierDeleteUseCase {

    private final SupplierGateway supplierGateway;

    public void execute(final String supplierCpnj) {

        supplierGateway.deleteSupplier(supplierCpnj);
    }
}
