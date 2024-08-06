package com.demo.graphqlvsrest.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "authors")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthdate;
    private String nationality;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookEntity> books;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AwardEntity> awards;

    public Long getId() {
        return id;
    }

    public AuthorEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AuthorEntity setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public AuthorEntity setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public AuthorEntity setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public Set<BookEntity> getBooks() {
        return books;
    }

    public AuthorEntity setBooks(Set<BookEntity> books) {
        this.books = books;
        return this;
    }

    public Set<AwardEntity> getAwards() {
        return awards;
    }

    public AuthorEntity setAwards(Set<AwardEntity> awards) {
        this.awards = awards;
        return this;
    }
}
