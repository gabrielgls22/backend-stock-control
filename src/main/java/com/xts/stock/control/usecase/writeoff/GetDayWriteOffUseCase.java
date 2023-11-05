package com.xts.stock.control.usecase.writeoff;

import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;
import com.xts.stock.control.usecase.writeoff.gateway.WriteOffGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetDayWriteOffUseCase {

    private final WriteOffGateway writeOffGateway;

    public List<WriteOffDomain> execute(final String firstDay, final String lastDay) {

        return writeOffGateway.getAllWriteOffs(firstDay, lastDay);
    }
}

