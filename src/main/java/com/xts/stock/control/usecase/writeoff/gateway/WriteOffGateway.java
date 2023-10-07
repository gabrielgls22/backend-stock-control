package com.xts.stock.control.usecase.writeoff.gateway;

import com.xts.stock.control.usecase.writeoff.domain.DeleteWriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;

import java.util.List;

public interface WriteOffGateway {
    void registerWriteOff(WriteOffDomain requestDomain);

    List<WriteOffDomain> getAllWriteOffs(String writeOffDate);

    void deleteWriteOff(DeleteWriteOffDomain requestDomain);
}
