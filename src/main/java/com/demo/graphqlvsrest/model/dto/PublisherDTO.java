package com.demo.graphqlvsrest.model.dto;

import java.util.List;

public record PublisherDTO(Long id, String name, String address, String contact, List<BookDTO> books) {}