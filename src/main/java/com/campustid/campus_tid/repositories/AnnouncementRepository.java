package com.campustid.campus_tid.repositories;

import com.campustid.campus_tid.models.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Long> {}
