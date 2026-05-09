package com.campustid.campus_tid.repositories;

import com.campustid.campus_tid.models.CourseEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
	List<CourseEntity> findByCategoryId(Long categoryId);

	long countByCategoryId(Long categoryId);
}
