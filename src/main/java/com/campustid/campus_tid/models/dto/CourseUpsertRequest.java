package com.campustid.campus_tid.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record CourseUpsertRequest(
	@NotBlank @Size(max = 180) String title,
	@NotBlank @Size(max = 2000) String description,
	@NotNull Long categoryId,
	LocalDate startDate,
	LocalDate endDate,
	Integer capacity,
	Boolean active
) {}
