package com.campustid.campus_tid.models.dto;

import jakarta.validation.constraints.NotNull;

public record EnrollmentCreateRequest(@NotNull Long userId, @NotNull Long courseId) {}
