package com.xts.stock.control.entrypoint.costumer;

import com.xts.stock.control.entrypoint.costumer.dto.*;
import com.xts.stock.control.entrypoint.costumer.mapper.CostumerEntrypointMapper;
import com.xts.stock.control.usecase.costumer.*;
import com.xts.stock.control.usecase.costumer.domain.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/costumer")
public class CostumerController {

    private final CostumerEntrypointMapper costumerEntrypointMapper;

    private final CostumerRegisterUseCase costumerRegisterUseCase;

    private final GetAllCostumersUseCase getAllCostumersUseCase;

    private final CostumerUpdateUseCase costumerUpdateUseCase;

    private final TagUpdateUseCase tagUpdateUseCase;

    private final CostumerDeleteUseCase costumerDeleteUseCase;

    private final TagDeleteUseCase tagDeleteUseCase;

    private final TagAddUseCase tagAddUseCase;


    @PostMapping("/register")
    @Operation(summary = "Register new customer",
            description = "Should register new customer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private void registerNewCostumer(@RequestBody @Valid final CostumerDto requestDto) {

        final CostumerDomain requestDomain =
                costumerEntrypointMapper.createCostumerRequestDtoToDomain(requestDto);

        costumerRegisterUseCase.execute(requestDomain);
    }

    @GetMapping
    @Operation(summary = "Get all customer",
            description = "Should get all customer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private @ResponseBody List<CostumerDto> registerNewCostumer() {

        final List<CostumerDomain> responseDomain = getAllCostumersUseCase.execute();

        return costumerEntrypointMapper.getAllCostumersDomainToDto(responseDomain);
    }

    @PostMapping("/update")
    @Operation(summary = "Customer update",
            description = "Should update a customer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private void updateCostumerInformations(@RequestBody @Valid final CostumerUpdateRequestDto requestDto) {

        final CostumerUpdateRequestDomain requestDomain =
                costumerEntrypointMapper.updateCostumerRequestDtoToDomain(requestDto);

        costumerUpdateUseCase.execute(requestDomain);
    }

    @PostMapping("/update/tag")
    @Operation(summary = "Material update",
            description = "Should update a tag",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private void updateTagInformations(@RequestBody @Valid final TagUpdateRequestDto requestDto) {

        final TagUpdateRequestDomain requestDomain =
                costumerEntrypointMapper.updateTagRequestDtoToDomain(requestDto);

        tagUpdateUseCase.execute(requestDomain);
    }

    @DeleteMapping("/{costumerCnpj}")
    @Operation(summary = "Delete customer",
            description = "Should delete a customer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private void deleteCostumer(@PathVariable("costumerCnpj") @Valid @NotBlank final String costumerCnpj) {

        costumerDeleteUseCase.execute(costumerCnpj);
    }

    @PostMapping("/delete-tag")
    @Operation(summary = "Delete tag",
            description = "Should delete a tag",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private void deleteTag(@RequestBody @Valid final TagDeleteRequestDto requestDto) {

        final TagDeleteRequestDomain requestDomain =
                costumerEntrypointMapper.deleteTagRequestDtoToDomain(requestDto);

        tagDeleteUseCase.execute(requestDomain);
    }

    @PostMapping("/add-tag")
    @Operation(summary = "Add tag",
            description = "Should add a tag",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private void addTag(@RequestBody @Valid final TagAddRequestDto requestDto) {

        final TagAddRequestDomain requestDomain =
                costumerEntrypointMapper.addTagRequestDtoToDomain(requestDto);

        tagAddUseCase.execute(requestDomain);
    }
}
