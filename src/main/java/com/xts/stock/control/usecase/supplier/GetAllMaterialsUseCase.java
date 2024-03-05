package com.xts.stock.control.usecase.supplier;

import com.xts.stock.control.usecase.supplier.domain.MaterialDomain;
import com.xts.stock.control.usecase.supplier.domain.SupplierDomain;
import com.xts.stock.control.usecase.supplier.gateway.SupplierGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllMaterialsUseCase {

    private final SupplierGateway supplierGateway;

    public List<MaterialDomain> execute() {
        final List<SupplierDomain> allSuppliers = supplierGateway.getAllSuppliers();

        final List<MaterialDomain> allMaterials = new ArrayList<>();

        allSuppliers.forEach(supplier -> allMaterials.addAll(supplier.getMaterialList()));

        return allMaterials;
    }
}
