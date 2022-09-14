package com.globalsprint.globalsprint1.repository;

import com.globalsprint.globalsprint1.model.Story;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StoryRepository extends JpaRepository<Story, Long> {

    @Query(value = "SELECT s FROM Story s " +
            "WHERE s.user.id = :userId OR s.type = 'PUBLIC' OR s.id = (SELECT ss.story.id FROM StorySubscription ss WHERE ss.userId = :userId)")
    Page<Story> getAllStories(Pageable pageable, @Param("userId") long userId);

}
