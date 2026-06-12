package com.campustid.campus_tid.models.dto;

import java.time.Instant;

public record ForumCommentResponse(
	Long id,
	Long authorId,
	String authorName,
	String content,
	Instant createdAt
) {}
