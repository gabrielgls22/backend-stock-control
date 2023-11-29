package com.xts.stock.control.entrypoint.writeoff.mapper;

import com.xts.stock.control.entrypoint.writeoff.dto.DeleteWriteOffDto;
import com.xts.stock.control.entrypoint.writeoff.dto.WriteOffDayDto;
import com.xts.stock.control.entrypoint.writeoff.dto.WriteOffDto;
import com.xts.stock.control.entrypoint.writeoff.dto.WriteOffMaterialsDto;
import com.xts.stock.control.usecase.writeoff.domain.DeleteWriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffMaterialsDomain;
import com.xts.stock.control.utils.Utils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public List<WriteOffDayDto> getAllWriteOffsDomainToDto(final List<WriteOffDomain> writeOffDomainList) {
        final List<WriteOffDayDto> writeOffDayDtoList = new ArrayList<>();

        writeOffDomainList.forEach(writeOffDomain -> {
            final WriteOffDayDto writeOffDayDto = WriteOffDayDto.builder()
                    .writeOffDate(formatDateToDayMonthYear(writeOffDomain.getWriteOffDate()))
                    .writeOffCode(writeOffDomain.getWriteOffCode())
                    .costumerCnpj(Utils.cnpjRegex(writeOffDomain.getCostumerCnpj()))
                    .costumerName(writeOffDomain.getCostumerName())
                    .tagCode(writeOffDomain.getTagCode())
                    .tagName(writeOffDomain.getTagName())
                    .serviceOrder(writeOffDomain.getServiceOrder())
                    .materials(responseWriteOffMaterialsDomainToDto(writeOffDomain.getMaterials()))
                    .build();

            writeOffDayDtoList.add(writeOffDayDto);
        });

        return writeOffDayDtoList;
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

    protected List<WriteOffMaterialsDomain> responseTagListDtoToDomain(
            final List<WriteOffMaterialsDto> writeOffDtoList) {
        final List<WriteOffMaterialsDomain> writeOffMaterialsDomainList = new ArrayList<>();

        writeOffDtoList.forEach(writeOffDto -> {
            final WriteOffMaterialsDomain writeOffDomain = WriteOffMaterialsDomain.builder()
                    .barCode(writeOffDto.getBarCode().trim())
                    .lengthUsed(writeOffDto.getLengthUsed().trim())
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
                    .lengthUsed(materialDomain.getLengthUsed())
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
