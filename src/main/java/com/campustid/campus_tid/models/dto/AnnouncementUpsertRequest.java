package com.campustid.campus_tid.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record AnnouncementUpsertRequest(
	@NotBlank @Size(max = 200) String title,
	@NotBlank @Size(max = 4000) String content,
	@NotNull LocalDate date
) {}
