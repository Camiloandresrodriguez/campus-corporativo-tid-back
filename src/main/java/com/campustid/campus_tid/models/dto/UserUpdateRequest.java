package com.campustid.campus_tid.models.dto;

import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
	@Size(max = 120) String firstName,
	@Size(max = 120) String lastName,
	@Size(max = 40) String phone,
	@Size(max = 40) String document,
	@Size(max = 240) String address,
	@Size(max = 40) String role
) {}
