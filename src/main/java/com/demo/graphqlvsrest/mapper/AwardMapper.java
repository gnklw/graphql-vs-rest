package com.demo.graphqlvsrest.mapper;

import com.demo.graphqlvsrest.model.dto.AwardDTO;
import com.demo.graphqlvsrest.model.entity.AwardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class})
public interface AwardMapper {
    AwardDTO toDTO(AwardEntity awardEntity);
    AwardEntity toEntity(AwardDTO awardDTO);
}
