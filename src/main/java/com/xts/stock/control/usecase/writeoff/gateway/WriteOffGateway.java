package com.xts.stock.control.usecase.writeoff.gateway;

import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;

public interface WriteOffGateway {
    void registerWriteOff(WriteOffDomain requestDomain);
}
