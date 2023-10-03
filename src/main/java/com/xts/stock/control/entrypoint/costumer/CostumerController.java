package com.xts.stock.control.entrypoint.costumer;

import com.xts.stock.control.entrypoint.costumer.dto.*;
import com.xts.stock.control.entrypoint.costumer.mapper.CostumerEntrypointMapper;
import com.xts.stock.control.usecase.costumer.*;
import com.xts.stock.control.usecase.costumer.domain.*;
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
    @ResponseStatus(HttpStatus.CREATED)
    private void registerNewCostumer(@RequestBody @Valid final CostumerDto requestDto) {

        final CostumerDomain requestDomain =
                costumerEntrypointMapper.createCostumerRequestDtoToDomain(requestDto);

        costumerRegisterUseCase.execute(requestDomain);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<CostumerDto> registerNewCostumer() {

        final List<CostumerDomain> responseDomain = getAllCostumersUseCase.execute();

        return costumerEntrypointMapper.getAllCostumersDomainToDto(responseDomain);
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    private void updateCostumerInformations(@RequestBody @Valid final CostumerUpdateRequestDto requestDto) {

        final CostumerUpdateRequestDomain requestDomain =
                costumerEntrypointMapper.updateCostumerRequestDtoToDomain(requestDto);

        costumerUpdateUseCase.execute(requestDomain);
    }

    @PostMapping("/update/tag")
    @ResponseStatus(HttpStatus.OK)
    private void updateTagInformations(@RequestBody @Valid final TagUpdateRequestDto requestDto) {

        final TagUpdateRequestDomain requestDomain =
                costumerEntrypointMapper.updateTagRequestDtoToDomain(requestDto);

        tagUpdateUseCase.execute(requestDomain);
    }

    @DeleteMapping("/{costumerCnpj}")
    @ResponseStatus(HttpStatus.OK)
    private void deleteCostumer(@PathVariable("costumerCnpj") @Valid @NotBlank final String costumerCnpj) {

        costumerDeleteUseCase.execute(costumerCnpj);
    }

    @PostMapping("/delete-tag")
    @ResponseStatus(HttpStatus.OK)
    private void deleteTag(@RequestBody @Valid final TagDeleteRequestDto requestDto) {

        final TagDeleteRequestDomain requestDomain =
                costumerEntrypointMapper.deleteTagRequestDtoToDomain(requestDto);

        tagDeleteUseCase.execute(requestDomain);
    }

    @PostMapping("/add-tag")
    @ResponseStatus(HttpStatus.OK)
    private void addTag(@RequestBody @Valid final TagAddRequestDto requestDto) {

        final TagAddRequestDomain requestDomain =
                costumerEntrypointMapper.addTagRequestDtoToDomain(requestDto);

        tagAddUseCase.execute(requestDomain);
    }
}
