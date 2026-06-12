package com.campustid.campus_tid.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ForumCommentCreateRequest(
	@NotNull Long authorId,
	@NotBlank @Size(max = 4000) String content
) {}
