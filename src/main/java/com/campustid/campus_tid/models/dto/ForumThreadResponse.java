package com.campustid.campus_tid.models.dto;

import java.time.Instant;
import java.util.List;

public record ForumThreadResponse(
	Long id,
	String title,
	String category,
	Long authorId,
	String authorName,
	int votes,
	long repliesCount,
	Instant createdAt,
	List<ForumCommentResponse> comments
) {}
