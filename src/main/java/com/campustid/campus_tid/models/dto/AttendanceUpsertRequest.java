package com.campustid.campus_tid.models.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record AttendanceUpsertRequest(
	@NotNull Long userId,
	@NotNull Long courseId,
	@NotNull LocalDate date,
	@NotNull String status
) {}
