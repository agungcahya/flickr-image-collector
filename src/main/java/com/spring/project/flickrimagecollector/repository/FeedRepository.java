package com.spring.project.flickrimagecollector.repository;

import com.spring.project.flickrimagecollector.entity.FeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends JpaRepository<FeedEntity, Integer> {
}
