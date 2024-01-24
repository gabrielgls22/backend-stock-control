package com.xts.stock.control.entrypoint.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xts.stock.control.dataprovider.auth.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponseDto {

    private String token;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Role> roles;
}
