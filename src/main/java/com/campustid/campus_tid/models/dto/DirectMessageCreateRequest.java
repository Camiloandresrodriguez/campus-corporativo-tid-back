package com.campustid.campus_tid.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DirectMessageCreateRequest(
	@NotNull Long senderId,
	@NotNull Long receiverId,
	@NotBlank @Size(max = 4000) String content
) {}
