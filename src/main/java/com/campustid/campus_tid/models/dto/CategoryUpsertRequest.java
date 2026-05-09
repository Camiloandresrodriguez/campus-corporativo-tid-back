package com.campustid.campus_tid.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryUpsertRequest(@NotBlank @Size(max = 120) String name) {}
