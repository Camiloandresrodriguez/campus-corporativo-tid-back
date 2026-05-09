package com.campustid.campus_tid.models.dto;

import java.time.Instant;
import java.time.LocalDate;

public record AnnouncementResponse(
	Long id,
	String title,
	String content,
	LocalDate date,
	Instant createdAt
) {}
