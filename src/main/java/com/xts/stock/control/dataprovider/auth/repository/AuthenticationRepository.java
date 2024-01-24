package com.xts.stock.control.dataprovider.auth.repository;

import com.xts.stock.control.dataprovider.auth.entity.UserAuthEntity;
import com.xts.stock.control.entrypoint.interceptor.exceptions.LoginDetailsException;
import com.xts.stock.control.entrypoint.interceptor.exceptions.UsernameAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthenticationRepository {

    private final AuthenticationDbRepository authenticationDbRepository;

    public UserAuthEntity getLoginDetails(final String username) {

        return authenticationDbRepository.findById(username).orElseThrow(LoginDetailsException::new);
    }

    public void createUserCredentials(final UserAuthEntity createDetailsEntity) {

        authenticationDbRepository.findById(createDetailsEntity.getUsername())
                .ifPresentOrElse(userDetails -> {
                            throw new UsernameAlreadyExistException();
                        },
                        () -> authenticationDbRepository.save(createDetailsEntity));
    }
}
