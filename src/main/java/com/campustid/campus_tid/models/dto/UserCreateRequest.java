package com.campustid.campus_tid.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
	@NotBlank @Size(max = 120) String firstName,
	@NotBlank @Size(max = 120) String lastName,
	@NotBlank @Email @Size(max = 180) String email,
	@NotBlank @Size(min = 6, max = 72) String password,
	@NotBlank @Size(min = 6, max = 72) String confirmPassword,
	@Size(max = 40) String phone,
	@Size(max = 40) String document,
	@Size(max = 240) String address
) {}
