package com.xts.stock.control.usecase.costumer;

import com.xts.stock.control.usecase.costumer.gateway.CostumerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CostumerDeleteUseCase {

    private final CostumerGateway costumerGateway;

    public void execute(final String costumerCpnj) {

        costumerGateway.deleteCostumer(costumerCpnj);
    }
}
