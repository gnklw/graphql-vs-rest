package com.demo.graphqlvsrest.repo;

import com.demo.graphqlvsrest.model.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
