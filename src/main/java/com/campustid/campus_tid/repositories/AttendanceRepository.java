package com.campustid.campus_tid.repositories;

import com.campustid.campus_tid.models.AttendanceEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
	List<AttendanceEntity> findByUserId(Long userId);
}
