package com.xts.stock.control.entrypoint.writeoff.mapper;

import com.xts.stock.control.entrypoint.writeoff.dto.DeleteWriteOffDto;
import com.xts.stock.control.entrypoint.writeoff.dto.WriteOffDayDto;
import com.xts.stock.control.entrypoint.writeoff.dto.WriteOffDto;
import com.xts.stock.control.usecase.writeoff.domain.DeleteWriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;

import java.util.List;

public interface WriteOffEntrypointMapper {
    WriteOffDomain createWriteOffRequestDtoToDomain(WriteOffDto requestDto);

    List<WriteOffDayDto> getAllWriteOffsDomainToDto(List<WriteOffDomain> responseDomain);

    DeleteWriteOffDomain deleteWriteOffDtoToDomain(DeleteWriteOffDto requestDto);
}
