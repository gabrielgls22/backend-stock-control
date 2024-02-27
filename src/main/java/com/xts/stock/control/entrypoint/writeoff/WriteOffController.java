package com.xts.stock.control.entrypoint.writeoff;

import com.xts.stock.control.entrypoint.writeoff.dto.DeleteWriteOffDto;
import com.xts.stock.control.entrypoint.writeoff.dto.WriteOffSearchResponseDto;
import com.xts.stock.control.entrypoint.writeoff.dto.WriteOffDto;
import com.xts.stock.control.entrypoint.writeoff.dto.WriteOffSearchRequestDto;
import com.xts.stock.control.entrypoint.writeoff.mapper.WriteOffEntrypointMapper;
import com.xts.stock.control.usecase.writeoff.GetWriteOffUseCase;
import com.xts.stock.control.usecase.writeoff.WriteOffDeleteUseCase;
import com.xts.stock.control.usecase.writeoff.WriteOffRegisterUseCase;
import com.xts.stock.control.usecase.writeoff.domain.DeleteWriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffSearchRequestDomain;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/write-off")
public class WriteOffController {

    private final WriteOffEntrypointMapper writeOffEntrypointMapper;

    private final WriteOffRegisterUseCase writeOffRegisterUseCase;

    private final GetWriteOffUseCase getWriteOffUseCase;

    private final WriteOffDeleteUseCase writeOffDeleteUseCase;


    @PostMapping("/register")
    @Operation(summary = "Register new write off",
            description = "Should register new writeOff",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    public void registerNewWriteOff(@RequestBody @Valid @NotNull final WriteOffDto requestDto) {

        final WriteOffDomain requestDomain =
                writeOffEntrypointMapper.createWriteOffRequestDtoToDomain(requestDto);

        writeOffRegisterUseCase.execute(requestDomain);
    }

    @PostMapping("/search")
    @Operation(summary = "Get write offs by search parameters",
            description = "Should get write offs by search parameters",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    public @ResponseBody @NotNull List<WriteOffSearchResponseDto> registerNewWriteOff(
            @RequestParam(value = "serviceOrder", required = false) final String serviceOrder,
            @RequestBody(required = false) @Valid final WriteOffSearchRequestDto requestDto) {

        final WriteOffSearchRequestDomain requestDomain =
                writeOffEntrypointMapper.searchRequestDtoToDomain(requestDto);

        final List<WriteOffDomain> responseDomain = getWriteOffUseCase.execute(requestDomain, serviceOrder);

        return writeOffEntrypointMapper.getAllWriteOffsDomainToDto(responseDomain);
    }

    @PostMapping("/delete")
    @Operation(summary = "Delete write off",
            description = "Should delete a specific writeOff",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    public void deleteWriteOff(@RequestBody @Valid @NotNull final DeleteWriteOffDto requestDto) {

        final DeleteWriteOffDomain requestDomain = writeOffEntrypointMapper.deleteWriteOffDtoToDomain(requestDto);

        writeOffDeleteUseCase.execute(requestDomain);
    }
}
