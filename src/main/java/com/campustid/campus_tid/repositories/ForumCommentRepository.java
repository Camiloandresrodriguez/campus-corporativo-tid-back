package com.campustid.campus_tid.repositories;

import com.campustid.campus_tid.models.ForumCommentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumCommentRepository extends JpaRepository<ForumCommentEntity, Long> {
	List<ForumCommentEntity> findByThreadIdOrderByCreatedAtAsc(Long threadId);

	long countByThreadId(Long threadId);
}
