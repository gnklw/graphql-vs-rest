package com.demo.graphqlvsrest.repo;

import com.demo.graphqlvsrest.model.entity.AwardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwardRepository extends JpaRepository<AwardEntity, Long> {
}
