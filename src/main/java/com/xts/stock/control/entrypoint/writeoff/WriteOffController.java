package com.xts.stock.control.entrypoint.writeoff;

import com.xts.stock.control.entrypoint.writeoff.dto.WriteOffDayDto;
import com.xts.stock.control.entrypoint.writeoff.dto.WriteOffDto;
import com.xts.stock.control.entrypoint.writeoff.mapper.WriteOffEntrypointMapper;
import com.xts.stock.control.usecase.writeoff.GetDayWriteOffUseCase;
import com.xts.stock.control.usecase.writeoff.WriteOffDeleteUseCase;
import com.xts.stock.control.usecase.writeoff.WriteOffRegisterUseCase;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/write-off")
public class WriteOffController {

    private final WriteOffEntrypointMapper writeOffEntrypointMapper;

    private final WriteOffRegisterUseCase writeOffRegisterUseCase;

    private final GetDayWriteOffUseCase getDayWriteOffUseCase;

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
    private void registerNewWriteOff(@RequestBody @Valid final WriteOffDto requestDto) {

        final WriteOffDomain requestDomain =
                writeOffEntrypointMapper.createWriteOffRequestDtoToDomain(requestDto);

        writeOffRegisterUseCase.execute(requestDomain);
    }

    @GetMapping("/{writeOffDate}")
    @Operation(summary = "Get all write offs",
            description = "Should get all writeOffs",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private List<WriteOffDayDto> registerNewWriteOff(
            @PathVariable("writeOffDate") @Valid @NotBlank final String writeOffDate) {

        final List<WriteOffDomain> responseDomain = getDayWriteOffUseCase.execute(writeOffDate);

        return writeOffEntrypointMapper.getAllWriteOffsDomainToDto(responseDomain);
    }

    @DeleteMapping("/{writeOffCnpj}")
    @Operation(summary = "Delete write off",
            description = "Should delete a specific writeOff",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    private void deleteWriteOff(@PathVariable("writeOffCnpj") @Valid @NotBlank final String writeOffCnpj) {

        writeOffDeleteUseCase.execute(writeOffCnpj);
    }
}
