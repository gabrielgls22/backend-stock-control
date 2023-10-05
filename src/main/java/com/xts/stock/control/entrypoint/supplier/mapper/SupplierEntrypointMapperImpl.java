package com.xts.stock.control.entrypoint.supplier.mapper;

import com.xts.stock.control.entrypoint.supplier.dto.*;
import com.xts.stock.control.usecase.supplier.domain.*;
import com.xts.stock.control.utils.Utils;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class SupplierEntrypointMapperImpl implements SupplierEntrypointMapper{
    @Override
    public SupplierDomain createSupplierRequestDtoToDomain(final SupplierDto requestDto) {
        final SupplierDomain.SupplierDomainBuilder supplierRequestDomainBuilder =
                SupplierDomain.builder();

        if (Objects.nonNull(requestDto)) {
            supplierRequestDomainBuilder.supplierName(requestDto.getSupplierName().trim());
            supplierRequestDomainBuilder.supplierCnpj(
                    Utils.removeSignals(requestDto.getSupplierCnpj().trim()));
            supplierRequestDomainBuilder.materialList(responseMaterialListDtoToDomain(requestDto.getMaterialList()));
        }

        return supplierRequestDomainBuilder.build();
    }

    @Override
    public List<SupplierDto> getAllSuppliersDomainToDto(final List<SupplierDomain> responseDomain) {
        final List<SupplierDto> supplierDtoList = new ArrayList<>();

        responseDomain.forEach(supplierDomain -> {
            final SupplierDto supplierDto = SupplierDto.builder()
                    .supplierCnpj(Utils.cnpjRegex(supplierDomain.getSupplierCnpj()))
                    .supplierName(supplierDomain.getSupplierName())
                    .materialList(responseMaterialListDomainToDto(supplierDomain.getMaterialList()))
                    .build();

            supplierDtoList.add(supplierDto);
        });

        return supplierDtoList;
    }

    @Override
    public SupplierUpdateRequestDomain updateSupplierRequestDtoToDomain(final SupplierUpdateRequestDto requestDto) {
        final SupplierUpdateRequestDomain.SupplierUpdateRequestDomainBuilder supplierUpdateRequestDomainBuilder =
                SupplierUpdateRequestDomain.builder();

        if (Objects.nonNull(requestDto)) {
            supplierUpdateRequestDomainBuilder.cnpj(Utils.removeSignals(requestDto.getCnpj().trim()));
            supplierUpdateRequestDomainBuilder.newCnpj(Utils.removeSignals(requestDto.getNewCnpj().trim()));
            supplierUpdateRequestDomainBuilder.newSupplier(requestDto.getNewSupplier().trim());
        }

        return supplierUpdateRequestDomainBuilder.build();
    }

    @Override
    public MaterialUpdateRequestDomain updateMaterialRequestDtoToDomain(final MaterialUpdateRequestDto requestDto) {
        final MaterialUpdateRequestDomain.MaterialUpdateRequestDomainBuilder materialUpdateRequestDomainBuilder =
                MaterialUpdateRequestDomain.builder();

        if (Objects.nonNull(requestDto)) {
            materialUpdateRequestDomainBuilder.cnpj(Utils.removeSignals(requestDto.getCnpj().trim()));
            materialUpdateRequestDomainBuilder.code(requestDto.getCode().trim());
            materialUpdateRequestDomainBuilder.name(requestDto.getName().trim());
            materialUpdateRequestDomainBuilder.description(requestDto.getDescription());
        }

        return materialUpdateRequestDomainBuilder.build();
    }

    @Override
    public MaterialDeleteRequestDomain deleteMaterialRequestDtoToDomain(final MaterialDeleteRequestDto requestDto) {
        final MaterialDeleteRequestDomain.MaterialDeleteRequestDomainBuilder materialDeleteRequestDomainBuilder =
                MaterialDeleteRequestDomain.builder();

        if (Objects.nonNull(requestDto)) {
            materialDeleteRequestDomainBuilder.supplierCnpj(Utils.removeSignals(requestDto.getSupplierCnpj()));
            materialDeleteRequestDomainBuilder.materialCode(requestDto.getMaterialCode());
        }

        return materialDeleteRequestDomainBuilder.build();
    }

    @Override
    public MaterialAddRequestDomain addMaterialRequestDtoToDomain(final MaterialAddRequestDto requestDto) {
        final MaterialAddRequestDomain.MaterialAddRequestDomainBuilder materialAddRequestDomainBuilder =
                MaterialAddRequestDomain.builder();

        if (Objects.nonNull(requestDto)) {
            materialAddRequestDomainBuilder.supplierCnpj(Utils.removeSignals(requestDto.getSupplierCnpj().trim()));
            materialAddRequestDomainBuilder.code(Utils.generateUniqueNumber());
            materialAddRequestDomainBuilder.name(requestDto.getName().trim());
            materialAddRequestDomainBuilder.description(requestDto.getDescription().trim());
        }

        return materialAddRequestDomainBuilder.build();
    }

    protected List<MaterialDomain> responseMaterialListDtoToDomain(final List<MaterialDto> materialDtoList) {
        final List<MaterialDomain> materialDomainList = new ArrayList<>();

        materialDtoList.forEach(material -> {
            final MaterialDomain materialDomain = MaterialDomain.builder()
                    .code(Utils.generateUniqueNumber())
                    .name(material.getName().trim())
                    .description(material.getDescription().trim())
                    .build();

            materialDomainList.add(materialDomain);
        });

        return materialDomainList;
    }

    protected List<MaterialDto> responseMaterialListDomainToDto(final List<MaterialDomain> materialDomainList) {
        final List<MaterialDto> materialDtoList = new ArrayList<>();

        materialDomainList.forEach(material -> {
            final MaterialDto materialDto = MaterialDto.builder()
                    .code(material.getCode())
                    .name(material.getName())
                    .description(material.getDescription())
                    .build();

            materialDtoList.add(materialDto);
        });

        return materialDtoList;
    }
}
