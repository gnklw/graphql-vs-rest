package com.demo.graphqlvsrest.model.dto;

import java.time.LocalDate;
import java.util.List;

public record BookDTO(
        Long id,
        String title,
        LocalDate publicationDate,
        String genre,
        AuthorDTO author,
        List<ReviewDTO> reviews,
        List<PublisherDTO> publishers) {
}
