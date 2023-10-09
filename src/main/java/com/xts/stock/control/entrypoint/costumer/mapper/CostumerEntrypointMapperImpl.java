package com.xts.stock.control.entrypoint.costumer.mapper;

import com.xts.stock.control.entrypoint.costumer.dto.*;
import com.xts.stock.control.usecase.costumer.domain.*;
import com.xts.stock.control.utils.Utils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CostumerEntrypointMapperImpl implements CostumerEntrypointMapper {

    @Override
    public CostumerDomain createCostumerRequestDtoToDomain(final CostumerDto requestDto) {
        final CostumerDomain.CostumerDomainBuilder costumerDomainBuilder =
                CostumerDomain.builder();

        if (Objects.nonNull(requestDto)) {
            costumerDomainBuilder.costumerCnpj(Utils.removeSignals(requestDto.getCostumerCnpj().trim()));
            costumerDomainBuilder.costumerName(requestDto.getCostumerName().trim());
            costumerDomainBuilder.tagList(responseTagListDtoToDomain(requestDto.getTagList()));
        }

        return costumerDomainBuilder.build();
    }

    @Override
    public List<CostumerDto> getAllCostumersDomainToDto(final List<CostumerDomain> responseDomain) {
        final List<CostumerDto> costumerDtoList = new ArrayList<>();

        responseDomain.forEach(costumer -> {
            final CostumerDto costumerDto = CostumerDto.builder()
                    .costumerCnpj(Utils.cnpjRegex(costumer.getCostumerCnpj()))
                    .costumerName(costumer.getCostumerName())
                    .tagList(responseTagListDomainToDto(costumer.getTagList()))
                    .build();

            costumerDtoList.add(costumerDto);
        });

        return costumerDtoList;
    }

    @Override
    public CostumerUpdateRequestDomain updateCostumerRequestDtoToDomain(final CostumerUpdateRequestDto requestDto) {
        final CostumerUpdateRequestDomain.CostumerUpdateRequestDomainBuilder costumerUpdateRequestDomainBuilder =
                CostumerUpdateRequestDomain.builder();

        if (Objects.nonNull(requestDto)) {
            costumerUpdateRequestDomainBuilder.cnpj(Utils.removeSignals(requestDto.getCnpj().trim()));
            costumerUpdateRequestDomainBuilder.newCnpj(Utils.removeSignals(requestDto.getNewCnpj().trim()));
            costumerUpdateRequestDomainBuilder.newCostumer(requestDto.getNewCostumer().trim());
        }

        return costumerUpdateRequestDomainBuilder.build();
    }

    @Override
    public TagUpdateRequestDomain updateTagRequestDtoToDomain(final TagUpdateRequestDto requestDto) {
        final TagUpdateRequestDomain.TagUpdateRequestDomainBuilder tagUpdateRequestDomainBuilder =
                TagUpdateRequestDomain.builder();

        if (Objects.nonNull(requestDto)) {
            tagUpdateRequestDomainBuilder.cnpj(Utils.removeSignals(requestDto.getCnpj().trim()));
            tagUpdateRequestDomainBuilder.code(requestDto.getCode().trim());
            tagUpdateRequestDomainBuilder.name(requestDto.getName().trim());
        }

        return tagUpdateRequestDomainBuilder.build();
    }

    @Override
    public TagDeleteRequestDomain deleteTagRequestDtoToDomain(final TagDeleteRequestDto requestDto) {
        final TagDeleteRequestDomain.TagDeleteRequestDomainBuilder tagDeleteRequestDomainBuilder =
                TagDeleteRequestDomain.builder();

        if (Objects.nonNull(requestDto)) {
            tagDeleteRequestDomainBuilder.costumerCnpj(Utils.removeSignals(requestDto.getCostumerCnpj().trim()));
            tagDeleteRequestDomainBuilder.tagCode(requestDto.getTagCode().trim());
        }

        return tagDeleteRequestDomainBuilder.build();
    }

    @Override
    public TagAddRequestDomain addTagRequestDtoToDomain(final TagAddRequestDto requestDto) {
        final TagAddRequestDomain.TagAddRequestDomainBuilder tagAddRequestDomainBuilder =
                TagAddRequestDomain.builder();

        if (Objects.nonNull(requestDto)) {
            tagAddRequestDomainBuilder.costumerCnpj(Utils.removeSignals(requestDto.getCostumerCnpj().trim()));
            tagAddRequestDomainBuilder.code(
                    !Strings.isBlank(requestDto.getCode()) ? requestDto.getCode().trim() :
                            Utils.generateUniqueNumber());
            tagAddRequestDomainBuilder.name(requestDto.getName().trim());
        }

        return tagAddRequestDomainBuilder.build();
    }

    protected List<TagDomain> responseTagListDtoToDomain(final List<TagDto> tagDtoList) {
        final List<TagDomain> tagDomainList = new ArrayList<>();

        tagDtoList.forEach(tag -> {
            final TagDomain tagDomain = TagDomain.builder()
                    .code(!Strings.isBlank(tag.getCode()) ? tag.getCode().trim() : Utils.generateUniqueNumber())
                    .name(tag.getName().trim())
                    .build();

            tagDomainList.add(tagDomain);
        });

        return tagDomainList;
    }

    protected List<TagDto> responseTagListDomainToDto(final List<TagDomain> tagDomainList) {
        final List<TagDto> tagDtoList = new ArrayList<>();

        tagDomainList.forEach(tag -> {
            final TagDto tagDto = TagDto.builder()
                    .code(tag.getCode())
                    .name(tag.getName())
                    .build();

            tagDtoList.add(tagDto);
        });

        return tagDtoList;
    }
}
