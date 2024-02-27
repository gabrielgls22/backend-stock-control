package com.xts.stock.control.entrypoint.writeoff.mapper;

import com.xts.stock.control.entrypoint.writeoff.dto.DeleteWriteOffDto;
import com.xts.stock.control.entrypoint.writeoff.dto.WriteOffSearchResponseDto;
import com.xts.stock.control.entrypoint.writeoff.dto.WriteOffDto;
import com.xts.stock.control.entrypoint.writeoff.dto.WriteOffSearchRequestDto;
import com.xts.stock.control.usecase.writeoff.domain.DeleteWriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffSearchRequestDomain;

import java.util.List;

public interface WriteOffEntrypointMapper {
    WriteOffDomain createWriteOffRequestDtoToDomain(WriteOffDto requestDto);

    List<WriteOffSearchResponseDto> getAllWriteOffsDomainToDto(List<WriteOffDomain> responseDomain);

    DeleteWriteOffDomain deleteWriteOffDtoToDomain(DeleteWriteOffDto requestDto);

    WriteOffSearchRequestDomain searchRequestDtoToDomain(WriteOffSearchRequestDto requestDto);
}
