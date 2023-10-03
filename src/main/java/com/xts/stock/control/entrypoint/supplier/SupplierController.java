package com.xts.stock.control.entrypoint.supplier;

import com.xts.stock.control.entrypoint.supplier.dto.*;
import com.xts.stock.control.entrypoint.supplier.mapper.SupplierEntrypointMapper;
import com.xts.stock.control.usecase.supplier.*;
import com.xts.stock.control.usecase.supplier.domain.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supplier")
public class SupplierController {

    private final SupplierEntrypointMapper supplierEntrypointMapper;

    private final SupplierRegisterUseCase supplierRegisterUseCase;

    private final GetAllSuppliersUseCase getAllSuppliersUseCase;

    private final SupplierUpdateUseCase supplierUpdateUseCase;

    private final MaterialUpdateUseCase materialUpdateUseCase;

    private final SupplierDeleteUseCase supplierDeleteUseCase;

    private final MaterialDeleteUseCase materialDeleteUseCase;

    private final MaterialAddUseCase materialAddUseCase;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    private void registerNewSupplier(@RequestBody @Valid final SupplierDto requestDto) {

        final SupplierDomain requestDomain =
                supplierEntrypointMapper.createSupplierRequestDtoToDomain(requestDto);

        supplierRegisterUseCase.execute(requestDomain);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<SupplierDto> registerNewSupplier() {

        final List<SupplierDomain> responseDomain = getAllSuppliersUseCase.execute();

        return supplierEntrypointMapper.getAllSuppliersDomainToDto(responseDomain);
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    private void updateSupplierInformations(@RequestBody @Valid final SupplierUpdateRequestDto requestDto) {

        final SupplierUpdateRequestDomain requestDomain =
                supplierEntrypointMapper.updateSupplierRequestDtoToDomain(requestDto);

        supplierUpdateUseCase.execute(requestDomain);
    }

    @PostMapping("/update/material")
    @ResponseStatus(HttpStatus.OK)
    private void updateMaterialInformations(@RequestBody @Valid final MaterialUpdateRequestDto requestDto) {

        final MaterialUpdateRequestDomain requestDomain =
                supplierEntrypointMapper.updateMaterialRequestDtoToDomain(requestDto);

        materialUpdateUseCase.execute(requestDomain);
    }

    @DeleteMapping("/{supplierCnpj}")
    @ResponseStatus(HttpStatus.OK)
    private void deleteSupplier(@PathVariable("supplierCnpj") @Valid @NotBlank final String supplierCnpj) {

        supplierDeleteUseCase.execute(supplierCnpj);
    }

    @PostMapping("/delete-material")
    @ResponseStatus(HttpStatus.OK)
    private void deleteMaterial(@RequestBody @Valid final MaterialDeleteRequestDto requestDto) {

        final MaterialDeleteRequestDomain requestDomain =
                supplierEntrypointMapper.deleteMaterialRequestDtoToDomain(requestDto);

        materialDeleteUseCase.execute(requestDomain);
    }

    @PostMapping("/add-material")
    @ResponseStatus(HttpStatus.OK)
    private void addMaterial(@RequestBody @Valid final MaterialAddRequestDto requestDto) {

        final MaterialAddRequestDomain requestDomain =
                supplierEntrypointMapper.addMaterialRequestDtoToDomain(requestDto);

        materialAddUseCase.execute(requestDomain);
    }
}
