package com.xts.stock.control.usecase.supplier;

import com.xts.stock.control.usecase.supplier.domain.MaterialDeleteRequestDomain;
import com.xts.stock.control.usecase.supplier.gateway.SupplierGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialDeleteUseCase {

    private final SupplierGateway supplierGateway;

    public void execute(final MaterialDeleteRequestDomain requestDomain) {

        supplierGateway.deleteMaterial(requestDomain);
    }
}
