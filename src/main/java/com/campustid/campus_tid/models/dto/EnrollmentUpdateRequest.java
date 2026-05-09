package com.campustid.campus_tid.models.dto;

import jakarta.validation.constraints.NotBlank;

public record EnrollmentUpdateRequest(@NotBlank String status) {}
