package com.demo.graphqlvsrest.mapper;

import com.demo.graphqlvsrest.model.dto.AuthorDTO;
import com.demo.graphqlvsrest.model.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mappings({
            @Mapping(target = "books", ignore = true),
            @Mapping(target = "awards", ignore = true)
    })
    AuthorDTO toDTO(AuthorEntity authorEntity);
    AuthorEntity toEntity(AuthorDTO authorDTO);
}
