package com.demo.graphqlvsrest.repo;

import com.demo.graphqlvsrest.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
