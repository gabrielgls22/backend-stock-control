package com.xts.stock.control.usecase.supplier;

import com.xts.stock.control.usecase.supplier.domain.MaterialAddRequestDomain;
import com.xts.stock.control.usecase.supplier.gateway.SupplierGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialAddUseCase {

    private final SupplierGateway supplierGateway;

    public void execute(final MaterialAddRequestDomain requestDomain) {

        supplierGateway.addMaterial(requestDomain);
    }
}
