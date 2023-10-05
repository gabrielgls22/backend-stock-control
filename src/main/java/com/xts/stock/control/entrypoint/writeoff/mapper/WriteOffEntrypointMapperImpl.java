package com.xts.stock.control.entrypoint.writeoff.mapper;

import com.xts.stock.control.entrypoint.writeoff.dto.WriteOffDayDto;
import com.xts.stock.control.entrypoint.writeoff.dto.WriteOffDto;
import com.xts.stock.control.entrypoint.writeoff.dto.WriteOffMaterialsDto;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffMaterialsDomain;
import com.xts.stock.control.utils.Utils;
import org.springframework.stereotype.Component;

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
            writeOffDomainBuilder.materials(responseTagListDtoToDomain(requestDto.getMaterials()));
        }

        return writeOffDomainBuilder.build();
    }

    @Override
    public List<WriteOffDayDto> getAllWriteOffsDomainToDto(final List<WriteOffDomain> responseDomain) {
        return null;
    }

    protected List<WriteOffMaterialsDomain> responseTagListDtoToDomain(
            final List<WriteOffMaterialsDto> writeOffDtoList) {
        final List<WriteOffMaterialsDomain> writeOffMaterialsDomainList = new ArrayList<>();

        writeOffDtoList.forEach(writeOffDto -> {
            final WriteOffMaterialsDomain writeOffDomain = WriteOffMaterialsDomain.builder()
                    .barCode(writeOffDto.getBarCode().trim())
                    .description(writeOffDto.getDescription().trim())
                    .build();

            writeOffMaterialsDomainList.add(writeOffDomain);
        });

        return writeOffMaterialsDomainList;
    }
}
