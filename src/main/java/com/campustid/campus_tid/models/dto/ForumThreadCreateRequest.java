package com.campustid.campus_tid.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ForumThreadCreateRequest(
	@NotBlank @Size(max = 220) String title,
	@NotBlank @Size(max = 80) String category,
	@NotBlank @Size(max = 4000) String content,
	@NotNull Long authorId
) {}
