package com.demo.graphqlvsrest.repo;

import com.demo.graphqlvsrest.model.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
