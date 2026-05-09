package com.campustid.campus_tid.models.dto;

import java.time.LocalDate;

public record AttendanceResponse(
	Long id,
	Long userId,
	Long courseId,
	String courseTitle,
	LocalDate date,
	String status
) {}
