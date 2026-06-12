package com.campustid.campus_tid.models.dto;

import java.time.LocalDate;

public record CourseResponse(
	Long id,
	String title,
	String description,
	Long categoryId,
	String categoryName,
	LocalDate startDate,
	LocalDate endDate,
	Integer capacity,
	boolean active,
	Long enrolledCount
) {}
