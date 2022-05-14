package com.taisiia.shop.mapper;

import com.taisiia.shop.domain.dao.Producer;
import com.taisiia.shop.domain.dto.ProducerDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProducerMapper {

    Producer toDao(ProducerDto producerDto);

    ProducerDto toDto(Producer producer);

    List<ProducerDto> toListDto(List<Producer> producers);
}
