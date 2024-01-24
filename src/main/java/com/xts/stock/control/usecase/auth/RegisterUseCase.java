package com.xts.stock.control.usecase.auth;

import com.xts.stock.control.config.authorization.JwtUseCase;
import com.xts.stock.control.dataprovider.auth.entity.UserAuthEntity;
import com.xts.stock.control.dataprovider.auth.entity.Role;
import com.xts.stock.control.dataprovider.auth.repository.AuthenticationRepository;
import com.xts.stock.control.usecase.auth.domain.AuthenticationResponseDomain;
import com.xts.stock.control.usecase.auth.domain.RegisterRequestDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUseCase {

    private final AuthenticationRepository authenticationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUseCase jwtUseCase;

    public AuthenticationResponseDomain execute(final RegisterRequestDomain requestDomain) {

        final UserAuthEntity userDetails = UserAuthEntity.builder()
                .username(requestDomain.getUsername())
                .password(passwordEncoder.encode(requestDomain.getPassword()))
                .admin(requestDomain.getAdmin())
                .role(Role.USER)
                .build();

        authenticationRepository.createUserCredentials(userDetails);

        final String jwtToken = jwtUseCase.generateToken(userDetails);

        return AuthenticationResponseDomain.builder()
                .token(jwtToken)
                .build();
    }
}
