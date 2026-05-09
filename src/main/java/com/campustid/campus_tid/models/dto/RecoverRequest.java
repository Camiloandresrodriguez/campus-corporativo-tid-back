package com.campustid.campus_tid.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RecoverRequest(@NotBlank @Email @Size(max = 180) String email) {}
