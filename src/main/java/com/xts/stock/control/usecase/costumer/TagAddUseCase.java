package com.xts.stock.control.usecase.costumer;

import com.xts.stock.control.usecase.costumer.domain.TagAddRequestDomain;
import com.xts.stock.control.usecase.costumer.gateway.CostumerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagAddUseCase {

    private final CostumerGateway costumerGateway;

    public void execute(final TagAddRequestDomain requestDomain) {

        costumerGateway.addTag(requestDomain);
    }
}
