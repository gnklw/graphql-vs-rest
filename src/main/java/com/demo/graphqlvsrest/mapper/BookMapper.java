package com.demo.graphqlvsrest.mapper;

import com.demo.graphqlvsrest.model.dto.BookDTO;
import com.demo.graphqlvsrest.model.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class})
public interface BookMapper {

    @Mappings({
        @Mapping(target = "publishers", ignore = true),
        @Mapping(target = "reviews", ignore = true)
    })
    BookDTO toDTO(BookEntity bookEntity);
    BookEntity toEntity(BookDTO bookDTO);
}
