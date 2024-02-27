package com.xts.stock.control.entrypoint.writeoff.mapper;

import com.xts.stock.control.entrypoint.writeoff.dto.*;
import com.xts.stock.control.usecase.writeoff.domain.DeleteWriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffMaterialsDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffSearchRequestDomain;
import com.xts.stock.control.utils.Utils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class WriteOffEntrypointMapperImpl implements WriteOffEntrypointMapper{
    @Override
    public WriteOffDomain createWriteOffRequestDtoToDomain(final WriteOffDto requestDto) {
        final WriteOffDomain.WriteOffDomainBuilder writeOffDomainBuilder =
                WriteOffDomain.builder();

        if (Objects.nonNull(requestDto)) {
            writeOffDomainBuilder.writeOffCode(Utils.generateUniqueNumber());
            writeOffDomainBuilder.costumerCnpj(Utils.removeSignals(requestDto.getCostumerCnpj().trim()));
            writeOffDomainBuilder.costumerName(requestDto.getCostumerName().trim());
            writeOffDomainBuilder.tagCode(requestDto.getTagCode().trim());
            writeOffDomainBuilder.tagName(requestDto.getTagName().trim());
            writeOffDomainBuilder.serviceOrder(requestDto.getServiceOrder().trim());
            writeOffDomainBuilder.materials(responseTagListDtoToDomain(requestDto.getMaterials()));
        }

        return writeOffDomainBuilder.build();
    }

    @Override
    public List<WriteOffSearchResponseDto> getAllWriteOffsDomainToDto(final List<WriteOffDomain> writeOffDomainList) {
        final List<WriteOffSearchResponseDto> writeOffSearchResponseDtoList = new ArrayList<>();

        writeOffDomainList.forEach(writeOffDomain -> {
            final WriteOffSearchResponseDto writeOffSearchResponseDto = WriteOffSearchResponseDto.builder()
                    .writeOffDate(formatDateToDayMonthYear(writeOffDomain.getWriteOffDate()))
                    .writeOffCode(writeOffDomain.getWriteOffCode())
                    .costumerCnpj(Utils.cnpjRegex(writeOffDomain.getCostumerCnpj()))
                    .costumerName(writeOffDomain.getCostumerName())
                    .tagCode(writeOffDomain.getTagCode())
                    .tagName(writeOffDomain.getTagName())
                    .serviceOrder(writeOffDomain.getServiceOrder())
                    .materials(responseWriteOffMaterialsDomainToDto(writeOffDomain.getMaterials()))
                    .build();

            writeOffSearchResponseDtoList.add(writeOffSearchResponseDto);
        });

        return writeOffSearchResponseDtoList;
    }

    @Override
    public DeleteWriteOffDomain deleteWriteOffDtoToDomain(final DeleteWriteOffDto requestDto) {
        final DeleteWriteOffDomain.DeleteWriteOffDomainBuilder writeOffDomainBuilder =
                DeleteWriteOffDomain.builder();

        if (Objects.nonNull(requestDto)) {
            writeOffDomainBuilder.writeOffDate(requestDto.getWriteOffDate());
            writeOffDomainBuilder.writeOffCode(requestDto.getWriteOffCode());
        }

        return writeOffDomainBuilder.build();
    }

    @Override
    public WriteOffSearchRequestDomain searchRequestDtoToDomain(final WriteOffSearchRequestDto requestDto) {

        return Optional.ofNullable(requestDto)
                .map(writeOffDto -> WriteOffSearchRequestDomain.builder()
                        .costumerCnpj(Utils.removeSignals(writeOffDto.getCostumerCnpj()))
                        .costumerName(writeOffDto.getCostumerName())
                        .tagCode(writeOffDto.getTagCode())
                        .tagName(writeOffDto.getTagName())
                        .material(writeOffDto.getMaterial())
                        .firstDay(writeOffDto.getFirstDay())
                        .lastDay(writeOffDto.getLastDay())
                        .build()
                ).orElse(WriteOffSearchRequestDomain.builder().build());
    }

    protected List<WriteOffMaterialsDomain> responseTagListDtoToDomain(
            final List<WriteOffMaterialsDto> writeOffDtoList) {
        final List<WriteOffMaterialsDomain> writeOffMaterialsDomainList = new ArrayList<>();

        writeOffDtoList.forEach(writeOffDto -> {
            final WriteOffMaterialsDomain writeOffDomain = WriteOffMaterialsDomain.builder()
                    .barCode(writeOffDto.getBarCode().trim())
                    .lengthUsed(writeOffDto.getLengthUsed())
                    .build();

            writeOffMaterialsDomainList.add(writeOffDomain);
        });

        return writeOffMaterialsDomainList;
    }

    protected List<WriteOffMaterialsDto> responseWriteOffMaterialsDomainToDto(
            final List<WriteOffMaterialsDomain> materialsDomainList) {

        final List<WriteOffMaterialsDto> writeOffMaterialsDtoList = new ArrayList<>();

        materialsDomainList.forEach(materialDomain -> {
            final WriteOffMaterialsDto materialsDto = WriteOffMaterialsDto.builder()
                    .barCode(materialDomain.getBarCode())
                    .name(materialDomain.getName())
                    .supplier(materialDomain.getSupplier())
                    .batch(materialDomain.getBatch())
                    .build();

            writeOffMaterialsDtoList.add(materialsDto);
        });

        return writeOffMaterialsDtoList;
    }

    private static String formatDateToDayMonthYear(final String date) {
        final LocalDate formattedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return formattedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
