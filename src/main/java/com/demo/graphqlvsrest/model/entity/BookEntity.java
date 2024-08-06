package com.demo.graphqlvsrest.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDate publicationDate;
    private String genre;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorEntity author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReviewEntity> reviews;

    @ManyToMany
    @JoinTable(
            name = "book_publisher",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private Set<PublisherEntity> publishers;

    @PreRemove
    private void preRemove() {
        this.publishers.clear();
    }

    public Long getId() {
        return id;
    }

    public BookEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public BookEntity setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public BookEntity setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public BookEntity setAuthor(AuthorEntity author) {
        this.author = author;
        return this;
    }

    public Set<ReviewEntity> getReviews() {
        return reviews;
    }

    public BookEntity setReviews(Set<ReviewEntity> reviews) {
        this.reviews = reviews;
        return this;
    }

    public Set<PublisherEntity> getPublishers() {
        return publishers;
    }

    public BookEntity setPublishers(Set<PublisherEntity> publishers) {
        this.publishers = publishers;
        return this;
    }
}
