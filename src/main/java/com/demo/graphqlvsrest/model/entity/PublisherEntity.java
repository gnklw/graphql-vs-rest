package com.demo.graphqlvsrest.model.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "publishers")
public class PublisherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String contact;

    @ManyToMany(mappedBy = "publishers")
    private Set<BookEntity> books;

    @PreRemove
    private void preRemove() {
        this.books.clear();
    }

    public Long getId() {
        return id;
    }

    public PublisherEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PublisherEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public PublisherEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public PublisherEntity setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public Set<BookEntity> getBooks() {
        return books;
    }

    public PublisherEntity setBooks(Set<BookEntity> books) {
        this.books = books;
        return this;
    }
}
