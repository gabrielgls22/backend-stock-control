package com.xts.stock.control.usecase.writeoff;

import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;
import com.xts.stock.control.usecase.writeoff.gateway.WriteOffGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class WriteOffRegisterUseCase {

    private final WriteOffGateway writeOffGateway;

    public void execute(final WriteOffDomain requestDomain) {

        requestDomain.setWriteOffDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        writeOffGateway.registerWriteOff(requestDomain);
    }

}
