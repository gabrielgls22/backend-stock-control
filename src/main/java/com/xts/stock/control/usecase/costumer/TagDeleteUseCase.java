package com.xts.stock.control.usecase.costumer;

import com.xts.stock.control.usecase.costumer.domain.TagDeleteRequestDomain;
import com.xts.stock.control.usecase.costumer.gateway.CostumerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagDeleteUseCase {

    private final CostumerGateway costumerGateway;

    public void execute(final TagDeleteRequestDomain requestDomain) {

        costumerGateway.deleteTag(requestDomain);
    }
}
