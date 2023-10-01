package com.xts.stock.control.usecase.supplier;

import com.xts.stock.control.usecase.supplier.domain.SupplierDomain;
import com.xts.stock.control.usecase.supplier.gateway.SupplierGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllSuppliersUseCase {

    private final SupplierGateway supplierGateway;

    public List<SupplierDomain> execute() {

        return supplierGateway.getAllSuppliers();
    }
}
