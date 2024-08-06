package com.demo.graphqlvsrest.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "reviews")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reviewerName;
    private int rating;
    private String comments;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    public Long getId() {
        return id;
    }

    public ReviewEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public ReviewEntity setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
        return this;
    }

    public int getRating() {
        return rating;
    }

    public ReviewEntity setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public ReviewEntity setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public BookEntity getBook() {
        return book;
    }

    public ReviewEntity setBook(BookEntity book) {
        this.book = book;
        return this;
    }
}
