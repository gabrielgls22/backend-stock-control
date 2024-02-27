package com.xts.stock.control.usecase.supplier;

import com.xts.stock.control.usecase.supplier.domain.MaterialUpdateRequestDomain;
import com.xts.stock.control.usecase.supplier.domain.SupplierDomain;
import com.xts.stock.control.usecase.supplier.gateway.SupplierGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialUpdateUseCase {

    private final SupplierGateway supplierGateway;

    public List<SupplierDomain> execute(final MaterialUpdateRequestDomain requestDomain) {

        supplierGateway.updateMaterial(requestDomain);

        return supplierGateway.getAllSuppliers();
    }
}
