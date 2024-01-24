package com.xts.stock.control.entrypoint.auth.mapper;

import com.xts.stock.control.entrypoint.auth.dto.AuthenticationRequestDto;
import com.xts.stock.control.entrypoint.auth.dto.AuthenticationResponseDto;
import com.xts.stock.control.entrypoint.auth.dto.RegisterRequestDto;
import com.xts.stock.control.usecase.auth.domain.AuthenticationRequestDomain;
import com.xts.stock.control.usecase.auth.domain.AuthenticationResponseDomain;
import com.xts.stock.control.usecase.auth.domain.RegisterRequestDomain;

public interface AuthenticationEntrypointMapper {

    AuthenticationRequestDomain authenticationRequestDtoToDomain(AuthenticationRequestDto requestDto);

    RegisterRequestDomain registerRequestDtoToDomain(RegisterRequestDto requestDto);

    AuthenticationResponseDto authenticationResponseDomainToDto(AuthenticationResponseDomain responseDomain);
}
