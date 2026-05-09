package com.campustid.campus_tid.models.dto;

import java.time.Instant;

public record UserResponse(
	Long id,
	String firstName,
	String lastName,
	String email,
	String phone,
	String document,
	String address,
	String role,
	Instant createdAt
) {}
