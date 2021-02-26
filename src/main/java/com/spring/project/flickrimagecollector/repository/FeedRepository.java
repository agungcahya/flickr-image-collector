package com.spring.project.flickrimagecollector.repository;

import com.spring.project.flickrimagecollector.entity.FeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedRepository extends JpaRepository<FeedEntity, Integer> {

    Optional<FeedEntity> findByAuthorId(String authorId);

    @Query(value = "select f from FeedEntity f where (:author is null or f.author like %:author%) and (:authorId is null or f.authorId like %:authorId%) " +
            "and (:title is null or f.title like %:title%) and (:tags is null or f.tags like %:tags%)")
    List<FeedEntity> getListFeed(String author, String authorId, String title, String tags);
}
