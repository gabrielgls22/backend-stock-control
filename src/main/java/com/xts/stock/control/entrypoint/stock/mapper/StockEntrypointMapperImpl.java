package com.xts.stock.control.entrypoint.stock.mapper;

import com.xts.stock.control.entrypoint.stock.dto.*;
import com.xts.stock.control.usecase.stock.domain.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
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

        final MaterialDetailsDomain materialDetailsDomain = MaterialDetailsDomain.builder()
                .length(requestDto.getLength())
                .width(requestDto.getWidth())
                .batchDetails(responseBatchDetailsDtoToDomain(requestDto))
                .build();

        materialDetailsDomainList.add(materialDetailsDomain);

        return materialDetailsDomainList;
    }

    private List<BatchDetailsDomain> responseBatchDetailsDtoToDomain(final StockDto requestDto) {
        final List<BatchDetailsDomain> batchDetailsDomainList = new ArrayList<>();

        final BatchDetailsDomain batchDetailsDomain = BatchDetailsDomain.builder()
                .batch(requestDto.getBatch())
                .barCodes(requestDto.getBarCodes())
                .build();

        batchDetailsDomainList.add(batchDetailsDomain);

        return batchDetailsDomainList;
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
                    .width(materialDetails.getWidth())
                    .length(materialDetails.getLength())
                    .quantity(materialDetails.getQuantity())
                    .build();

            materialDetailsDtoList.add(materialDetailsDto);
        });

        materialDetailsDtoList.sort(Comparator.comparing(MaterialDetailsDto::getWidth));

        return materialDetailsDtoList;
    }
}
