package com.campustid.campus_tid.controllers;

import com.campustid.campus_tid.models.dto.ForumCommentCreateRequest;
import com.campustid.campus_tid.models.dto.ForumThreadCreateRequest;
import com.campustid.campus_tid.models.dto.ForumThreadResponse;
import com.campustid.campus_tid.services.ForumService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/forums")
public class ForumsController {

	private final ForumService forumService;

	public ForumsController(ForumService forumService) {
		this.forumService = forumService;
	}

	@GetMapping
	public ResponseEntity<List<ForumThreadResponse>> list() {
		return ResponseEntity.ok(forumService.list());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ForumThreadResponse> get(@PathVariable Long id) {
		return ResponseEntity.ok(forumService.get(id));
	}

	@PostMapping
	public ResponseEntity<ForumThreadResponse> create(@Valid @RequestBody ForumThreadCreateRequest req) {
		return ResponseEntity.status(HttpStatus.CREATED).body(forumService.create(req));
	}

	@PostMapping("/{id}/vote")
	public ResponseEntity<ForumThreadResponse> vote(@PathVariable Long id) {
		return ResponseEntity.ok(forumService.vote(id));
	}

	@PostMapping("/{id}/comments")
	public ResponseEntity<ForumThreadResponse> comment(
		@PathVariable Long id,
		@Valid @RequestBody ForumCommentCreateRequest req
	) {
		return ResponseEntity.status(HttpStatus.CREATED).body(forumService.comment(id, req));
	}
}
