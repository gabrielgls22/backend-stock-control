package com.xts.stock.control.usecase.auth;

import com.xts.stock.control.config.authorization.JwtUseCase;
import com.xts.stock.control.dataprovider.auth.entity.UserAuthEntity;
import com.xts.stock.control.dataprovider.auth.repository.AuthenticationRepository;
import com.xts.stock.control.usecase.auth.domain.AuthenticationRequestDomain;
import com.xts.stock.control.usecase.auth.domain.AuthenticationResponseDomain;
import com.xts.stock.control.utils.CryptDecryptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationUseCase {

    @Value("${decrypt.key}")
    private String decryptKey;

    @Value("${decrypt.enabled}")
    private Boolean isDecryptEnabled;

    private final AuthenticationManager authenticationManager;
    private final AuthenticationRepository authenticationRepository;
    private final JwtUseCase jwtUseCase;

    public AuthenticationResponseDomain execute(final AuthenticationRequestDomain requestDomain) {

        if (isDecryptEnabled) {
            requestDomain.setUsername(CryptDecryptUtils.decrypt(requestDomain.getUsername(), decryptKey));
            requestDomain.setPassword(CryptDecryptUtils.decrypt(requestDomain.getPassword(), decryptKey));
        }

        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  requestDomain.getUsername(),
                  requestDomain.getPassword()));

        final UserAuthEntity userDetails = authenticationRepository.getLoginDetails(requestDomain.getUsername());

        final String jwtToken = jwtUseCase.generateToken(userDetails);

        return AuthenticationResponseDomain.builder()
                .token(jwtToken)
                .role(userDetails.getRole())
                .build();
    }
}
