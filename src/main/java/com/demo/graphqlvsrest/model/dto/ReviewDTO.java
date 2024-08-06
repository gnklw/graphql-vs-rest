package com.demo.graphqlvsrest.model.dto;

public record ReviewDTO(Long id, String reviewerName, int rating, String comments, BookDTO book) {}