package com.xts.stock.control.usecase.writeoff.gateway;

import com.xts.stock.control.usecase.writeoff.domain.DeleteWriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;

import java.util.List;

public interface WriteOffGateway {
    void registerWriteOff(WriteOffDomain requestDomain);

    List<WriteOffDomain> getWriteOffsByDate(String firstDay, String lastDay);

    void deleteWriteOff(DeleteWriteOffDomain requestDomain);

    WriteOffDomain getWriteOffByServiceOrder(String serviceOrder);

    void validateServiceOrderDuplicity(String serviceOrder);
}
