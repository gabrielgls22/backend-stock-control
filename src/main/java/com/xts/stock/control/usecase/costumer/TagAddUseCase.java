package com.xts.stock.control.usecase.costumer;

import com.xts.stock.control.entrypoint.interceptor.exceptions.TagAlreadyExistException;
import com.xts.stock.control.usecase.costumer.domain.CostumerDomain;
import com.xts.stock.control.usecase.costumer.domain.TagAddRequestDomain;
import com.xts.stock.control.usecase.costumer.gateway.CostumerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagAddUseCase {

    private final CostumerGateway costumerGateway;

    public void execute(final TagAddRequestDomain requestDomain) {

        validateExistingTag(requestDomain, costumerGateway.getAllCostumers());

        costumerGateway.addTag(requestDomain);
    }

    private void validateExistingTag(final TagAddRequestDomain requestDomain, final List<CostumerDomain> allCostumers) {

        allCostumers.forEach(customerResponse ->
                customerResponse.getTagList().forEach(tag -> {
                    if (tag.getCode().equalsIgnoreCase(requestDomain.getCode())) {
                        throw new TagAlreadyExistException(requestDomain.getName(), requestDomain.getCode());
                    }
                }));
    }
}
