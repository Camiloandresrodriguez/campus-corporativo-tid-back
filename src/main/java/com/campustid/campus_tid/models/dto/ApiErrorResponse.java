package com.campustid.campus_tid.models.dto;

import java.util.Map;

public record ApiErrorResponse(String message, Map<String, String> fieldErrors) {}
