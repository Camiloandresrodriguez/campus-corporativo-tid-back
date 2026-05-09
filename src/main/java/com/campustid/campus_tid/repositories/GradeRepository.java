package com.campustid.campus_tid.repositories;

import com.campustid.campus_tid.models.GradeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<GradeEntity, Long> {
	List<GradeEntity> findByUserId(Long userId);
}
