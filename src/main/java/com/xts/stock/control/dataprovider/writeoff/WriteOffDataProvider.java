package com.xts.stock.control.dataprovider.writeoff;

import com.xts.stock.control.dataprovider.writeoff.entity.WriteOffEntity;
import com.xts.stock.control.dataprovider.writeoff.mapper.WriteOffRepositoryMapper;
import com.xts.stock.control.dataprovider.writeoff.repository.WriteOffRepository;
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
}
