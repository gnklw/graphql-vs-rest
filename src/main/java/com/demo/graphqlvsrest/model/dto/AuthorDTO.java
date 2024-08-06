package com.demo.graphqlvsrest.model.dto;

import java.time.LocalDate;
import java.util.List;

public record AuthorDTO(Long id,
                        String name,
                        LocalDate birthdate,
                        String nationality,
                        List<BookDTO> books,
                        List<AwardDTO> awards) {
}