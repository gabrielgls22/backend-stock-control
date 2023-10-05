package com.xts.stock.control.dataprovider.writeoff.mapper;

import com.xts.stock.control.dataprovider.writeoff.entity.WriteOffEntity;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;

public interface WriteOffRepositoryMapper {
    WriteOffEntity registerWriteOffDomainToEntity(WriteOffDomain requestDomain);
}
