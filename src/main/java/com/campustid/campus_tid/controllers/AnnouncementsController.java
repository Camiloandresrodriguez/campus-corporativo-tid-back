package com.campustid.campus_tid.controllers;

import com.campustid.campus_tid.models.dto.AnnouncementResponse;
import com.campustid.campus_tid.models.dto.AnnouncementUpsertRequest;
import com.campustid.campus_tid.services.AnnouncementService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementsController {

	private final AnnouncementService announcementService;

	public AnnouncementsController(AnnouncementService announcementService) {
		this.announcementService = announcementService;
	}

	@GetMapping
	public ResponseEntity<List<AnnouncementResponse>> list(@RequestParam(required = false) Long userId) {
		return ResponseEntity.ok(announcementService.list(userId));
	}

	@GetMapping("/{id}")
	public ResponseEntity<AnnouncementResponse> get(@PathVariable Long id, @RequestParam(required = false) Long userId) {
		return ResponseEntity.ok(announcementService.get(id, userId));
	}

	@PostMapping
	public ResponseEntity<AnnouncementResponse> create(@Valid @RequestBody AnnouncementUpsertRequest req) {
		return ResponseEntity.status(HttpStatus.CREATED).body(announcementService.create(req));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AnnouncementResponse> update(
		@PathVariable Long id,
		@Valid @RequestBody AnnouncementUpsertRequest req
	) {
		return ResponseEntity.ok(announcementService.update(id, req));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		announcementService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{id}/read")
	public ResponseEntity<Void> markRead(@PathVariable Long id, @RequestParam Long userId) {
		announcementService.markRead(id, userId);
		return ResponseEntity.noContent().build();
	}
}
