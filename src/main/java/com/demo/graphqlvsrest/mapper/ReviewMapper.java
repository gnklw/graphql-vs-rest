package com.demo.graphqlvsrest.mapper;

import com.demo.graphqlvsrest.model.dto.ReviewDTO;
import com.demo.graphqlvsrest.model.entity.ReviewEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface ReviewMapper {
    ReviewDTO toDTO(ReviewEntity reviewEntity);
    ReviewEntity toEntity(ReviewDTO reviewDTO);
}
