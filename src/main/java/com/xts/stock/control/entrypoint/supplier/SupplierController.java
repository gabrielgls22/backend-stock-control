package com.xts.stock.control.entrypoint.supplier;

import com.xts.stock.control.entrypoint.supplier.dto.*;
import com.xts.stock.control.entrypoint.supplier.mapper.SupplierEntrypointMapper;
import com.xts.stock.control.usecase.supplier.*;
import com.xts.stock.control.usecase.supplier.domain.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
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
    @Operation(summary = "Register new supplier",
            description = "Should register new supplier",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private void registerNewSupplier(@RequestBody @Valid final SupplierDto requestDto) {

        final SupplierDomain requestDomain =
                supplierEntrypointMapper.createSupplierRequestDtoToDomain(requestDto);

        supplierRegisterUseCase.execute(requestDomain);
    }

    @GetMapping
    @Operation(summary = "Get all supplier",
            description = "Should get all supplier",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private @ResponseBody List<SupplierDto> getAllSuppliers() {

        final List<SupplierDomain> responseDomain = getAllSuppliersUseCase.execute();

        return supplierEntrypointMapper.getAllSuppliersDomainToDto(responseDomain);
    }

    @PostMapping("/update")
    @Operation(summary = "Supplier update",
            description = "Should update a supplier",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private void updateSupplierInformation(@RequestBody @Valid final SupplierUpdateRequestDto requestDto) {

        final SupplierUpdateRequestDomain requestDomain =
                supplierEntrypointMapper.updateSupplierRequestDtoToDomain(requestDto);

        supplierUpdateUseCase.execute(requestDomain);
    }

    @PostMapping("/update/material")
    @Operation(summary = "Material update",
            description = "Should update a material",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private void updateMaterialInformation(@RequestBody @Valid final MaterialUpdateRequestDto requestDto) {

        final MaterialUpdateRequestDomain requestDomain =
                supplierEntrypointMapper.updateMaterialRequestDtoToDomain(requestDto);

        materialUpdateUseCase.execute(requestDomain);
    }

    @DeleteMapping("/{supplierCnpj}")
    @Operation(summary = "Delete supplier",
            description = "Should delete a supplier",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private void deleteSupplier(@PathVariable("supplierCnpj") @Valid @NotBlank final String supplierCnpj) {

        supplierDeleteUseCase.execute(supplierCnpj);
    }

    @PostMapping("/delete-material")
    @Operation(summary = "Delete material",
            description = "Should delete a material",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private void deleteMaterial(@RequestBody @Valid final MaterialDeleteRequestDto requestDto) {

        final MaterialDeleteRequestDomain requestDomain =
                supplierEntrypointMapper.deleteMaterialRequestDtoToDomain(requestDto);

        materialDeleteUseCase.execute(requestDomain);
    }

    @PostMapping("/add-material")
    @Operation(summary = "Add material",
            description = "Should add a material",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private void addMaterial(@RequestBody @Valid final MaterialAddRequestDto requestDto) {

        final MaterialAddRequestDomain requestDomain =
                supplierEntrypointMapper.addMaterialRequestDtoToDomain(requestDto);

        materialAddUseCase.execute(requestDomain);
    }
}
