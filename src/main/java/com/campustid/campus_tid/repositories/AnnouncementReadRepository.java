package com.campustid.campus_tid.repositories;

import com.campustid.campus_tid.models.AnnouncementReadEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementReadRepository extends JpaRepository<AnnouncementReadEntity, Long> {
	List<AnnouncementReadEntity> findByUserId(Long userId);

	boolean existsByUserIdAndAnnouncementId(Long userId, Long announcementId);
}
