package com.xts.stock.control.entrypoint.supplier.mapper;

import com.xts.stock.control.entrypoint.supplier.dto.MaterialDto;
import com.xts.stock.control.entrypoint.supplier.dto.SupplierRequestDto;
import com.xts.stock.control.usecase.supplier.domain.MaterialDomain;
import com.xts.stock.control.usecase.supplier.domain.SupplierRequestDomain;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class SupplierEntrypointMapperImpl implements SupplierEntrypointMapper{
    @Override
    public SupplierRequestDomain requestDtoToDomain(final SupplierRequestDto requestDto) {
        final SupplierRequestDomain.SupplierRequestDomainBuilder supplierRequestDomainBuilder =
                SupplierRequestDomain.builder();

        if (Objects.nonNull(requestDto)) {
            supplierRequestDomainBuilder.supplierName(requestDto.getSupplierName().trim());
            supplierRequestDomainBuilder.supplierCnpj(
                    requestDto.getSupplierCnpj().trim().replaceAll("[./-]", ""));
            supplierRequestDomainBuilder.materialList(responseMaterialListDtoToDomain(requestDto.getMaterialList()));
        }

        return supplierRequestDomainBuilder.build();
    }

    protected List<MaterialDomain> responseMaterialListDtoToDomain(final List<MaterialDto> materialDtoList) {
        final List<MaterialDomain> materialDomainList = new ArrayList<>();

        materialDtoList.forEach(material -> {
            final MaterialDomain materialDomain = MaterialDomain.builder()
                    .code(material.getCode().trim())
                    .name(material.getName().trim())
                    .description(material.getDescription().trim())
                    .build();

            materialDomainList.add(materialDomain);
        });

        return materialDomainList;
    }
}
