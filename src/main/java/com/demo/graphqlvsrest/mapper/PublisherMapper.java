package com.demo.graphqlvsrest.mapper;

import com.demo.graphqlvsrest.model.dto.PublisherDTO;
import com.demo.graphqlvsrest.model.entity.PublisherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    @Mapping(target = "books", ignore = true)
    PublisherDTO toDTO(PublisherEntity publisherEntity);
    PublisherEntity toEntity(PublisherDTO publisherDTO);
}
