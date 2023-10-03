package com.xts.stock.control.usecase.costumer;

import com.xts.stock.control.usecase.costumer.domain.TagUpdateRequestDomain;
import com.xts.stock.control.usecase.costumer.gateway.CostumerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagUpdateUseCase {

    private final CostumerGateway costumerGateway;

    public void execute(final TagUpdateRequestDomain requestDomain) {

        costumerGateway.updateTag(requestDomain);
    }
}
