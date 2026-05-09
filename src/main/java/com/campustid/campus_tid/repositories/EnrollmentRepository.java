package com.campustid.campus_tid.repositories;

import com.campustid.campus_tid.models.EnrollmentEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {
	List<EnrollmentEntity> findByUserId(Long userId);

	Optional<EnrollmentEntity> findByUserIdAndCourseId(Long userId, Long courseId);
}
