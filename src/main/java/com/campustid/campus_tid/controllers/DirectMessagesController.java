package com.campustid.campus_tid.controllers;

import com.campustid.campus_tid.models.dto.DirectMessageCreateRequest;
import com.campustid.campus_tid.models.dto.DirectMessageResponse;
import com.campustid.campus_tid.models.dto.UserResponse;
import com.campustid.campus_tid.services.DirectMessageService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/direct-messages")
public class DirectMessagesController {

	private final DirectMessageService directMessageService;

	public DirectMessagesController(DirectMessageService directMessageService) {
		this.directMessageService = directMessageService;
	}

	@GetMapping("/contacts")
	public ResponseEntity<List<UserResponse>> contacts(@RequestParam Long userId) {
		return ResponseEntity.ok(directMessageService.contacts(userId));
	}

	@GetMapping
	public ResponseEntity<List<DirectMessageResponse>> conversation(
		@RequestParam Long userId,
		@RequestParam Long contactId
	) {
		return ResponseEntity.ok(directMessageService.conversation(userId, contactId));
	}

	@PostMapping
	public ResponseEntity<DirectMessageResponse> send(@Valid @RequestBody DirectMessageCreateRequest req) {
		return ResponseEntity.status(HttpStatus.CREATED).body(directMessageService.send(req));
	}
}
