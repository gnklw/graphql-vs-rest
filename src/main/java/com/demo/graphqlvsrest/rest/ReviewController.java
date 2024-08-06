package com.demo.graphqlvsrest.rest;

import com.demo.graphqlvsrest.mapper.ReviewMapper;
import com.demo.graphqlvsrest.model.dto.ReviewDTO;
import com.demo.graphqlvsrest.model.entity.ReviewEntity;
import com.demo.graphqlvsrest.repo.ReviewRepository;
import com.demo.graphqlvsrest.repo.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("restReviewController")
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final ReviewMapper reviewMapper;

    public ReviewController(ReviewRepository reviewRepository, BookRepository bookRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.reviewMapper = reviewMapper;
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ReviewDTO getReviewById(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::toDTO)
                .orElse(null);
    }

    @PostMapping
    public ReviewDTO createReview(@RequestBody ReviewDTO reviewDTO) {
        ReviewEntity reviewEntity = reviewMapper.toEntity(reviewDTO);
        reviewEntity.setBook(bookRepository.findById(reviewDTO.book().id()).orElse(null));
        return reviewMapper.toDTO(reviewRepository.save(reviewEntity));
    }

    @PutMapping("/{id}")
    public ReviewDTO updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDetails) {
        ReviewEntity reviewEntity = reviewRepository.findById(id).orElse(null);
        if (reviewEntity != null) {
            reviewEntity.setReviewerName(reviewDetails.reviewerName());
            reviewEntity.setRating(reviewDetails.rating());
            reviewEntity.setComments(reviewDetails.comments());
            reviewEntity.setBook(bookRepository.findById(reviewDetails.book().id()).orElse(null));
            return reviewMapper.toDTO(reviewRepository.save(reviewEntity));
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewRepository.deleteById(id);
    }
}