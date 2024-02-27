package com.xts.stock.control.usecase.writeoff;

import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffSearchRequestDomain;
import com.xts.stock.control.usecase.writeoff.gateway.WriteOffGateway;
import lombok.RequiredArgsConstructor;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetWriteOffUseCase {

    private final WriteOffGateway writeOffGateway;

    public List<WriteOffDomain> execute(final WriteOffSearchRequestDomain requestDomain, final String serviceOrder) {

        return Optional.ofNullable(serviceOrder)
                .map(serviceOrderSearch -> {
                            final List<WriteOffDomain> writeOffByServiceOrder = new ArrayList<>();

                            writeOffByServiceOrder.add(writeOffGateway.getWriteOffByServiceOrder(serviceOrderSearch));

                            return writeOffByServiceOrder;
                        }
                ).orElseGet(() ->
                        getFilteredWriteOffList(requestDomain)
                );
    }

    private List<WriteOffDomain> getFilteredWriteOffList(
            final WriteOffSearchRequestDomain requestDomain) {

        final List<WriteOffDomain> writeOffsByDate =
                writeOffGateway.getWriteOffsByDate(requestDomain.getFirstDay(), requestDomain.getLastDay());

        return writeOffsByDate.stream()
                .filter(writeOffDomain ->
                        Strings.isEmpty(requestDomain.getCostumerCnpj()) ||
                                writeOffDomain.getCostumerCnpj().equals(requestDomain.getCostumerCnpj()))
                .filter(writeOffDomain ->
                        Strings.isEmpty(requestDomain.getTagCode()) ||
                                writeOffDomain.getTagCode().equals(requestDomain.getTagCode()))
                .filter(writeOffDomain ->
                        Strings.isEmpty(requestDomain.getMaterial()) ||
                                writeOffDomain.getMaterials().stream()
                                        .anyMatch(writeOffMaterial ->
                                                writeOffMaterial.getName().equalsIgnoreCase(requestDomain.getMaterial())))
                .toList();
    }
}

