package com.xts.stock.control.usecase.costumer;

import com.xts.stock.control.usecase.costumer.domain.CostumerUpdateRequestDomain;
import com.xts.stock.control.usecase.costumer.gateway.CostumerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CostumerUpdateUseCase {

    private final CostumerGateway costumerGateway;

    public void execute(final CostumerUpdateRequestDomain requestDomain) {

        costumerGateway.updateCostumer(requestDomain);
    }
}
