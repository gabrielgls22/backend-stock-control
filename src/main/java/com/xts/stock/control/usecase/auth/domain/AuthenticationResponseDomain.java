package com.xts.stock.control.usecase.auth.domain;

import com.xts.stock.control.dataprovider.auth.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponseDomain {

    private String token;
    private Role role;
}
