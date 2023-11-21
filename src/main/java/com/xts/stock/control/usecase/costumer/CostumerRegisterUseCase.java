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

        validateExistingTag(requestDomain, costumerGateway.getAllCostumers());

        costumerGateway.createNewCostumer(requestDomain);
    }

    private void validateExistingTag(final CostumerDomain requestDomain, final List<CostumerDomain> allCostumers) {

        requestDomain.getTagList().forEach(customerDomain ->
                allCostumers.forEach(customerResponse ->
                        customerResponse.getTagList().forEach(tag -> {
                            if (tag.getCode().equalsIgnoreCase(customerDomain.getCode())) {
                                throw new TagAlreadyExistException(customerDomain.getName(), customerDomain.getCode());
                            }
                        })));
    }
}
