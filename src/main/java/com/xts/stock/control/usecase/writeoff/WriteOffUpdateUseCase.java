package com.xts.stock.control.usecase.writeoff;

import com.xts.stock.control.usecase.writeoff.domain.UpdateWriteOffRequestDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;
import com.xts.stock.control.usecase.writeoff.gateway.WriteOffGateway;
import lombok.RequiredArgsConstructor;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WriteOffUpdateUseCase {

    private final WriteOffGateway writeOffGateway;

    public List<WriteOffDomain> execute(final UpdateWriteOffRequestDomain requestDomain) {

        writeOffGateway.updateWriteOffByDateAndWriteOffCode(requestDomain);

        return Optional.ofNullable(requestDomain.getSearchServiceOrder())
                .map(writeOffGateway::getWriteOffByServiceOrder)
                .orElseGet(() ->
                        getFilteredWriteOffList(requestDomain)
                );
    }

    private List<WriteOffDomain> getFilteredWriteOffList(
            final UpdateWriteOffRequestDomain requestDomain) {

        final List<WriteOffDomain> writeOffsByDate =
                writeOffGateway.getWriteOffsByFirstAndLastDay(requestDomain.getFirstDay(), requestDomain.getLastDay());

        return writeOffsByDate.stream()
                .filter(writeOffDomain ->
                        Strings.isEmpty(requestDomain.getSearchCostumerCnpj()) ||
                                writeOffDomain.getCostumerCnpj().equals(requestDomain.getSearchCostumerCnpj()))
                .filter(writeOffDomain ->
                        Strings.isEmpty(requestDomain.getSearchTagCode()) ||
                                writeOffDomain.getTagCode().equals(requestDomain.getSearchTagCode()))
                .filter(writeOffDomain ->
                        Strings.isEmpty(requestDomain.getSearchMaterial()) ||
                                writeOffDomain.getMaterials().stream()
                                        .anyMatch(writeOffMaterial ->
                                                writeOffMaterial.getName().equalsIgnoreCase(requestDomain.getSearchMaterial())))
                .toList();
    }
}

