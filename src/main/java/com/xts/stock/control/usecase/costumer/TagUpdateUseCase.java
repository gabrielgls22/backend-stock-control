package com.xts.stock.control.usecase.costumer;

import com.xts.stock.control.usecase.costumer.domain.CostumerDomain;
import com.xts.stock.control.usecase.costumer.domain.TagUpdateRequestDomain;
import com.xts.stock.control.usecase.costumer.gateway.CostumerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagUpdateUseCase {

    private final CostumerGateway costumerGateway;

    public List<CostumerDomain> execute(final TagUpdateRequestDomain requestDomain) {

        costumerGateway.updateTag(requestDomain);

        return costumerGateway.getAllCostumers();
    }
}
