package com.xts.stock.control.usecase.writeoff;

import com.xts.stock.control.usecase.writeoff.domain.DeleteWriteOffDomain;
import com.xts.stock.control.usecase.writeoff.gateway.WriteOffGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriteOffDeleteUseCase {

    private final WriteOffGateway writeOffGateway;

    public void execute(final DeleteWriteOffDomain requestDomain) {

        writeOffGateway.deleteWriteOff(requestDomain);
    }
}
