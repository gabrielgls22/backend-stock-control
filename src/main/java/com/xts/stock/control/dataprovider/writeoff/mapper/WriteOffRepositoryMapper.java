package com.xts.stock.control.dataprovider.writeoff.mapper;

import com.xts.stock.control.dataprovider.writeoff.entity.DeleteWriteOffEntity;
import com.xts.stock.control.dataprovider.writeoff.entity.WriteOffDetailsEntity;
import com.xts.stock.control.dataprovider.writeoff.entity.WriteOffEntity;
import com.xts.stock.control.usecase.writeoff.domain.DeleteWriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;

import java.util.List;

public interface WriteOffRepositoryMapper {
    WriteOffEntity registerWriteOffDomainToEntity(WriteOffDomain requestDomain);

    List<WriteOffDomain> getAllWriteOffsEntityToDomain(List<WriteOffDetailsEntity> responseEntity);

    DeleteWriteOffEntity deleteWriteOffDomainToEntity(DeleteWriteOffDomain requestDomain);

    WriteOffDomain writeOffByServiceOrderEntityToDomain(WriteOffDetailsEntity responseEntity);
}
