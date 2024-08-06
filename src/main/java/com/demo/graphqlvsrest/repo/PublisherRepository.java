package com.demo.graphqlvsrest.repo;

import com.demo.graphqlvsrest.model.entity.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {
}
