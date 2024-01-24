package com.xts.stock.control.usecase.costumer;

import com.xts.stock.control.usecase.costumer.domain.CostumerDomain;
import com.xts.stock.control.usecase.costumer.domain.TagDeleteRequestDomain;
import com.xts.stock.control.usecase.costumer.gateway.CostumerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagDeleteUseCase {

    private final CostumerGateway costumerGateway;

    public List<CostumerDomain> execute(final TagDeleteRequestDomain requestDomain) {

        costumerGateway.deleteTag(requestDomain);

        return costumerGateway.getAllCostumers();
    }
}
