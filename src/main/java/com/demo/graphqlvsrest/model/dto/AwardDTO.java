package com.demo.graphqlvsrest.model.dto;

public record AwardDTO(Long id, String name, int year, AuthorDTO author) {}