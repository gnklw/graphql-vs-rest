package com.demo.graphqlvsrest.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "awards")
public class AwardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int year;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorEntity author;

    public Long getId() {
        return id;
    }

    public AwardEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AwardEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getYear() {
        return year;
    }

    public AwardEntity setYear(int year) {
        this.year = year;
        return this;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public AwardEntity setAuthor(AuthorEntity author) {
        this.author = author;
        return this;
    }
}
