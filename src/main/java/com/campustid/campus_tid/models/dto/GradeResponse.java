package com.campustid.campus_tid.models.dto;

import java.time.LocalDate;

public record GradeResponse(
	Long id,
	Long userId,
	Long courseId,
	String courseTitle,
	Double score,
	String note,
	LocalDate date
) {}
