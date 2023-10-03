package com.xts.stock.control.usecase.costumer;

import com.xts.stock.control.usecase.costumer.domain.CostumerDomain;
import com.xts.stock.control.usecase.costumer.gateway.CostumerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCostumersUseCase {

    private final CostumerGateway costumerGateway;

    public List<CostumerDomain> execute() {

        return costumerGateway.getAllCostumers();
    }
}
