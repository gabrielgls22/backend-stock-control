package com.xts.stock.control.entrypoint.stock.mapper;

import com.xts.stock.control.entrypoint.stock.dto.*;
import com.xts.stock.control.usecase.stock.domain.DeleteMaterialStockDomain;
import com.xts.stock.control.usecase.stock.domain.MaterialDetailsDomain;
import com.xts.stock.control.usecase.stock.domain.StockDomain;
import com.xts.stock.control.usecase.stock.domain.StockMaterialDomain;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class StockEntrypointMapperImpl implements StockEntrypointMapper{
    @Override
    public StockDomain registerStockRequestDtoToDomain(final StockDto requestDto) {
        final StockDomain.StockDomainBuilder stockDomainBuilder = StockDomain.builder();

        if (Objects.nonNull(requestDto)) {
            stockDomainBuilder.supplierName(requestDto.getSupplierName());
            stockDomainBuilder.materialList(responseMaterialListDomain(requestDto));
        }

        return stockDomainBuilder.build();
    }

    @Override
    public List<StockResponseDto> getAllStockDomainToEntity(final List<StockDomain> responseDomain) {
        final List<StockResponseDto> stockResponseDtoList = new ArrayList<>();

        responseDomain.forEach(stockDomain -> {
            final StockResponseDto stockDto = StockResponseDto.builder()
                    .supplierName(stockDomain.getSupplierName())
                    .materialList(responseMaterialListDomainToDto(stockDomain.getMaterialList()))
                    .build();

            stockResponseDtoList.add(stockDto);
        });

        return stockResponseDtoList;
    }

    @Override
    public DeleteMaterialStockDomain deleteMaterialStockDtoToDomain(final DeleteMaterialStockDto requestDto) {
        final DeleteMaterialStockDomain.DeleteMaterialStockDomainBuilder deleteMaterialStockDomainBuilder =
                DeleteMaterialStockDomain.builder();

        if (Objects.nonNull(requestDto)) {
            deleteMaterialStockDomainBuilder.supplierName(requestDto.getSupplierName());
            deleteMaterialStockDomainBuilder.materialName(requestDto.getMaterialName());
            deleteMaterialStockDomainBuilder.barCode(requestDto.getBarCode());
        }

        return deleteMaterialStockDomainBuilder.build();
    }

    private List<StockMaterialDomain> responseMaterialListDomain(final StockDto requestDto) {
        final List<StockMaterialDomain> stockMaterialDomainList = new ArrayList<>();

        final StockMaterialDomain stockMaterialDomain = StockMaterialDomain.builder()
                .materialName(requestDto.getMaterialName())
                .materialsDetails(responseMaterialDetailsDtoToDomain(requestDto))
                .build();

        stockMaterialDomainList.add(stockMaterialDomain);

        return stockMaterialDomainList;
    }

    private List<MaterialDetailsDomain> responseMaterialDetailsDtoToDomain(final StockDto requestDto) {
        final List<MaterialDetailsDomain> materialDetailsDomainList = new ArrayList<>();

        final List<String> barCodeList = new ArrayList<>();

        barCodeList.add(requestDto.getBarCode());

        final MaterialDetailsDomain materialDetailsDomain = MaterialDetailsDomain.builder()
                .batch(requestDto.getBatch())
                .length(requestDto.getLength())
                .width(requestDto.getWidth())
                .barCodes(barCodeList)
                .build();

        materialDetailsDomainList.add(materialDetailsDomain);

        return materialDetailsDomainList;
    }

    private List<StockMaterialDto> responseMaterialListDomainToDto(final List<StockMaterialDomain> materialDomainList) {
        final List<StockMaterialDto> stockMaterialDtoList = new ArrayList<>();

        materialDomainList.forEach(materialDomain -> {
            final StockMaterialDto materialDto = StockMaterialDto.builder()
                    .materialName(materialDomain.getMaterialName())
                    .materialsDetails(responseMaterialDetailsDomainToDto(materialDomain.getMaterialsDetails()))
                    .build();

            stockMaterialDtoList.add(materialDto);
        });

        return stockMaterialDtoList;
    }

    private List<MaterialDetailsDto> responseMaterialDetailsDomainToDto(
            final List<MaterialDetailsDomain> materialDetailsDomain) {
        final List<MaterialDetailsDto> materialDetailsDtoList = new ArrayList<>();

        materialDetailsDomain.forEach(materialDetails -> {
            final MaterialDetailsDto materialDetailsDto = MaterialDetailsDto.builder()
                    .batch(materialDetails.getBatch())
                    .width(materialDetails.getWidth())
                    .length(materialDetails.getLength())
                    .quantity(materialDetails.getQuantity())
                    .barCodes(materialDetails.getBarCodes())
                    .build();

            materialDetailsDtoList.add(materialDetailsDto);
        });

        return materialDetailsDtoList;
    }
}
