package com.campustid.campus_tid.models.dto;

import java.time.Instant;

public record DirectMessageResponse(
	Long id,
	Long senderId,
	Long receiverId,
	String content,
	Instant sentAt
) {}
