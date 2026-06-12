package com.campustid.campus_tid.repositories;

import com.campustid.campus_tid.models.ForumThreadEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumThreadRepository extends JpaRepository<ForumThreadEntity, Long> {
	List<ForumThreadEntity> findAllByOrderByCreatedAtDesc();
}
