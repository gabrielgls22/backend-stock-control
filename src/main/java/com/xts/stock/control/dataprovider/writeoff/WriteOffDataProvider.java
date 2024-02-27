package com.xts.stock.control.dataprovider.writeoff;

import com.xts.stock.control.dataprovider.writeoff.entity.DeleteWriteOffEntity;
import com.xts.stock.control.dataprovider.writeoff.entity.WriteOffDetailsEntity;
import com.xts.stock.control.dataprovider.writeoff.entity.WriteOffEntity;
import com.xts.stock.control.dataprovider.writeoff.mapper.WriteOffRepositoryMapper;
import com.xts.stock.control.dataprovider.writeoff.repository.WriteOffRepository;
import com.xts.stock.control.usecase.writeoff.domain.DeleteWriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.WriteOffDomain;
import com.xts.stock.control.usecase.writeoff.gateway.WriteOffGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WriteOffDataProvider implements WriteOffGateway {

    private final WriteOffRepositoryMapper writeOffRepositoryMapper;

    private final WriteOffRepository writeOffRepository;

    @Override
    public void registerWriteOff(final WriteOffDomain requestDomain) {

        final WriteOffEntity requestEntity =
                writeOffRepositoryMapper.registerWriteOffDomainToEntity(requestDomain);

        writeOffRepository.registerWriteOff(requestEntity);
    }

    @Override
    public List<WriteOffDomain> getWriteOffsByDate(final String firstDay, final String lastDay) {
        final List<WriteOffDetailsEntity> responseEntity = writeOffRepository.getWriteOffsByDate(firstDay, lastDay);

        return writeOffRepositoryMapper.getAllWriteOffsEntityToDomain(responseEntity);
    }

    @Override
    public void deleteWriteOff(final DeleteWriteOffDomain requestDomain) {
        final DeleteWriteOffEntity requestEntity =
                writeOffRepositoryMapper.deleteWriteOffDomainToEntity(requestDomain);

        writeOffRepository.deleteWriteOff(requestEntity);
    }

    @Override
    public WriteOffDomain getWriteOffByServiceOrder(final String serviceOrder) {
        final WriteOffDetailsEntity responseEntity = writeOffRepository.getWriteOffByServiceOrder(serviceOrder);

        return writeOffRepositoryMapper.writeOffByServiceOrderEntityToDomain(responseEntity);
    }
}
