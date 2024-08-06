package com.demo.graphqlvsrest.graphql;

import com.demo.graphqlvsrest.mapper.ReviewMapper;
import com.demo.graphqlvsrest.model.dto.ReviewDTO;
import com.demo.graphqlvsrest.model.entity.ReviewEntity;
import com.demo.graphqlvsrest.repo.BookRepository;
import com.demo.graphqlvsrest.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("graphqlReviewController")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewController(ReviewRepository reviewRepository, BookRepository bookRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.reviewMapper = reviewMapper;
    }

    @QueryMapping
    public List<ReviewDTO> allReviews() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toDTO)
                .toList();
    }

    @QueryMapping
    public ReviewDTO reviewById(@Argument Long id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::toDTO)
                .orElse(null);
    }

    @MutationMapping
    public ReviewDTO createReview(@Argument String reviewerName, @Argument int rating, @Argument String comments, @Argument Long bookId) {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewerName(reviewerName);
        reviewEntity.setRating(rating);
        reviewEntity.setComments(comments);
        reviewEntity.setBook(bookRepository.findById(bookId).orElse(null));
        return reviewMapper.toDTO(reviewRepository.save(reviewEntity));
    }

    @MutationMapping
    public ReviewDTO updateReview(@Argument Long id, @Argument String reviewerName, @Argument int rating, @Argument String comments, @Argument Long bookId) {
        ReviewEntity reviewEntity = reviewRepository.findById(id).orElse(null);
        if (reviewEntity != null) {
            reviewEntity.setReviewerName(reviewerName);
            reviewEntity.setRating(rating);
            reviewEntity.setComments(comments);
            reviewEntity.setBook(bookRepository.findById(bookId).orElse(null));
            return reviewMapper.toDTO(reviewRepository.save(reviewEntity));
        }
        return null;
    }

    @MutationMapping
    public Boolean deleteReview(@Argument Long id) {
        reviewRepository.deleteById(id);
        return true;
    }
}