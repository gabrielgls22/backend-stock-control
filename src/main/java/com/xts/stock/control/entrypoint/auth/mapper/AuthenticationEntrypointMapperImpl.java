package com.xts.stock.control.entrypoint.auth.mapper;

import com.xts.stock.control.entrypoint.auth.dto.AuthenticationRequestDto;
import com.xts.stock.control.entrypoint.auth.dto.AuthenticationResponseDto;
import com.xts.stock.control.entrypoint.auth.dto.RegisterRequestDto;
import com.xts.stock.control.usecase.auth.domain.AuthenticationRequestDomain;
import com.xts.stock.control.usecase.auth.domain.AuthenticationResponseDomain;
import com.xts.stock.control.usecase.auth.domain.RegisterRequestDomain;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AuthenticationEntrypointMapperImpl implements AuthenticationEntrypointMapper {
    @Override
    public AuthenticationRequestDomain authenticationRequestDtoToDomain(final AuthenticationRequestDto requestDto) {
        final AuthenticationRequestDomain.AuthenticationRequestDomainBuilder authenticationRequestDomainBuilder =
                AuthenticationRequestDomain.builder();

        if (Objects.nonNull(requestDto)) {
            authenticationRequestDomainBuilder.username(requestDto.getUsername());
            authenticationRequestDomainBuilder.password(requestDto.getPassword());
        }

        return authenticationRequestDomainBuilder.build();
    }

    @Override
    public RegisterRequestDomain registerRequestDtoToDomain(final RegisterRequestDto requestDto) {
        final RegisterRequestDomain.RegisterRequestDomainBuilder registerRequestDomainBuilder =
                RegisterRequestDomain.builder();

        if (Objects.nonNull(requestDto)) {
            registerRequestDomainBuilder.username(requestDto.getUsername());
            registerRequestDomainBuilder.password(requestDto.getPassword());
        }

        return registerRequestDomainBuilder.build();
    }

    @Override
    public AuthenticationResponseDto authenticationResponseDomainToDto(
            final AuthenticationResponseDomain responseDomain) {

        final AuthenticationResponseDto.AuthenticationResponseDtoBuilder authenticationResponseDtoBuilder =
                AuthenticationResponseDto.builder();

        if (Objects.nonNull(responseDomain)) {
            authenticationResponseDtoBuilder.token(responseDomain.getToken());
            authenticationResponseDtoBuilder.roles(List.of(responseDomain.getRole()));
        }

        return authenticationResponseDtoBuilder.build();
    }
}
