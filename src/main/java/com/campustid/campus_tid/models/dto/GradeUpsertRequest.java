package com.campustid.campus_tid.models.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record GradeUpsertRequest(
	@NotNull Long userId,
	@NotNull Long courseId,
	@NotNull Double score,
	String note,
	LocalDate date
) {}
