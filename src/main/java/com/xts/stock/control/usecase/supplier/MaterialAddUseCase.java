package com.xts.stock.control.usecase.supplier;

import com.xts.stock.control.usecase.supplier.domain.MaterialAddRequestDomain;
import com.xts.stock.control.usecase.supplier.domain.MaterialAddResponseDomain;
import com.xts.stock.control.usecase.supplier.domain.SupplierDomain;
import com.xts.stock.control.usecase.supplier.gateway.SupplierGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialAddUseCase {

    private final SupplierGateway supplierGateway;

    public List<SupplierDomain> execute(final MaterialAddRequestDomain requestDomain) {

        supplierGateway.addMaterial(requestDomain);

        return supplierGateway.getAllSuppliers();
    }
}
