package com.xts.stock.control.usecase.costumer;

import com.xts.stock.control.entrypoint.interceptor.exceptions.TagAlreadyExistException;
import com.xts.stock.control.usecase.costumer.domain.CostumerDomain;
import com.xts.stock.control.usecase.costumer.gateway.CostumerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CostumerRegisterUseCase {

    private final CostumerGateway costumerGateway;

    public void execute(final CostumerDomain requestDomain) {

        costumerGateway.createNewCostumer(requestDomain);
    }
}
