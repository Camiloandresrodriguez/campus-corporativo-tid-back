package com.campustid.campus_tid.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(
	@NotBlank @Size(min = 6, max = 72) String currentPassword,
	@NotBlank @Size(min = 6, max = 72) String newPassword,
	@NotBlank @Size(min = 6, max = 72) String confirmNewPassword
) {}
