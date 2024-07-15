package com.xts.stock.control.dataprovider.writeoff;

import com.xts.stock.control.dataprovider.writeoff.entity.DeleteWriteOffEntity;
import com.xts.stock.control.dataprovider.writeoff.entity.UpdateWriteOffRequestEntity;
import com.xts.stock.control.dataprovider.writeoff.entity.WriteOffDetailsEntity;
import com.xts.stock.control.dataprovider.writeoff.entity.WriteOffEntity;
import com.xts.stock.control.dataprovider.writeoff.mapper.WriteOffRepositoryMapper;
import com.xts.stock.control.dataprovider.writeoff.repository.WriteOffRepository;
import com.xts.stock.control.usecase.writeoff.domain.DeleteWriteOffDomain;
import com.xts.stock.control.usecase.writeoff.domain.UpdateWriteOffRequestDomain;
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
    public List<WriteOffDomain> getWriteOffsByFirstAndLastDay(final String firstDay, final String lastDay) {
        final List<WriteOffDetailsEntity> responseEntity = writeOffRepository.getWriteOffsByFirstAndLastDay(firstDay, lastDay);

        return writeOffRepositoryMapper.getWriteOffsEntityToDomain(responseEntity);
    }

    @Override
    public void deleteWriteOff(final DeleteWriteOffDomain requestDomain) {
        final DeleteWriteOffEntity requestEntity =
                writeOffRepositoryMapper.deleteWriteOffDomainToEntity(requestDomain);

        writeOffRepository.deleteWriteOff(requestEntity);
    }

    @Override
    public List<WriteOffDomain> getWriteOffByServiceOrder(final String serviceOrder) {
        final List<WriteOffDetailsEntity> responseEntity = writeOffRepository.getWriteOffByServiceOrder(serviceOrder);

        return writeOffRepositoryMapper.writeOffByServiceOrderEntityToDomain(responseEntity);
    }

    @Override
    public void validateServiceOrderDuplicity(final String serviceOrder) {

        writeOffRepository.validateServiceOrderDuplicity(serviceOrder);
    }

    @Override
    public void updateWriteOffByDateAndWriteOffCode(final UpdateWriteOffRequestDomain requestDomain) {
        final UpdateWriteOffRequestEntity requestEntity =
                writeOffRepositoryMapper.updateWriteOffRequestDomainToEntity(requestDomain);

        writeOffRepository.updateWriteOff(requestEntity);
    }
}
