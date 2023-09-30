package com.xts.stock.control.entrypoint.supplier;

import com.xts.stock.control.entrypoint.supplier.dto.SupplierRequestDto;
import com.xts.stock.control.entrypoint.supplier.mapper.SupplierEntrypointMapper;
import com.xts.stock.control.usecase.supplier.SupplierRegisterUseCase;
import com.xts.stock.control.usecase.supplier.domain.SupplierRequestDomain;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supplier")
public class SupplierController {

    private final SupplierEntrypointMapper supplierEntrypointMapper;

    private final SupplierRegisterUseCase supplierRegisterUseCase;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    private void registerNewSupplier(@RequestBody @NonNull final SupplierRequestDto requestDto) {

        final SupplierRequestDomain requestDomain =
                supplierEntrypointMapper.requestDtoToDomain(requestDto);

        supplierRegisterUseCase.execute(requestDomain);
    }
}
