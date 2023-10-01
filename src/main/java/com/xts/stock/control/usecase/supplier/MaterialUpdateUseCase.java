package com.xts.stock.control.usecase.supplier;

import com.xts.stock.control.usecase.supplier.domain.MaterialUpdateRequestDomain;
import com.xts.stock.control.usecase.supplier.gateway.SupplierGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialUpdateUseCase {

    private final SupplierGateway supplierGateway;

    public void execute(final MaterialUpdateRequestDomain requestDomain) {

        supplierGateway.updateMaterial(requestDomain);
    }
}
