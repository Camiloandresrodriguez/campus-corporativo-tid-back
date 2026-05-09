package com.campustid.campus_tid.models.dto;

import java.time.Instant;

public record EnrollmentResponse(
	Long id,
	Long userId,
	Long courseId,
	String courseTitle,
	String status,
	Instant enrolledAt
) {}
