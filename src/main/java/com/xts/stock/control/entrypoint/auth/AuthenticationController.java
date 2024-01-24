package com.xts.stock.control.entrypoint.auth;

import com.xts.stock.control.entrypoint.auth.dto.AuthenticationRequestDto;
import com.xts.stock.control.entrypoint.auth.dto.AuthenticationResponseDto;
import com.xts.stock.control.entrypoint.auth.dto.RegisterRequestDto;
import com.xts.stock.control.entrypoint.auth.mapper.AuthenticationEntrypointMapper;
import com.xts.stock.control.usecase.auth.AuthenticationUseCase;
import com.xts.stock.control.usecase.auth.RegisterUseCase;
import com.xts.stock.control.usecase.auth.domain.AuthenticationRequestDomain;
import com.xts.stock.control.usecase.auth.domain.RegisterRequestDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationEntrypointMapper authenticationEntrypointMapper;

    private final RegisterUseCase registerUseCase;

    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> registerUser(
            @RequestBody final RegisterRequestDto requestDto
    ) {
        final RegisterRequestDomain requestDomain =
                authenticationEntrypointMapper.registerRequestDtoToDomain(requestDto);

        final AuthenticationResponseDto responseDto = authenticationEntrypointMapper
                .authenticationResponseDomainToDto(registerUseCase.execute(requestDomain));

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticateUser(
            @RequestBody final AuthenticationRequestDto requestDto
    ) {
        final AuthenticationRequestDomain requestDomain =
                authenticationEntrypointMapper.authenticationRequestDtoToDomain(requestDto);

        final AuthenticationResponseDto responseDto = authenticationEntrypointMapper
                .authenticationResponseDomainToDto(authenticationUseCase.execute(requestDomain));

        return ResponseEntity.ok(responseDto);
    }
}
