package com.campustid.campus_tid.controllers;

import com.campustid.campus_tid.models.dto.EnrollmentCreateRequest;
import com.campustid.campus_tid.models.dto.EnrollmentResponse;
import com.campustid.campus_tid.models.dto.EnrollmentUpdateRequest;
import com.campustid.campus_tid.services.EnrollmentService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentsController {

	private final EnrollmentService enrollmentService;

	public EnrollmentsController(EnrollmentService enrollmentService) {
		this.enrollmentService = enrollmentService;
	}

	@GetMapping
	public ResponseEntity<List<EnrollmentResponse>> list(@RequestParam(required = false) Long userId) {
		return ResponseEntity.ok(enrollmentService.list(userId));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EnrollmentResponse> get(@PathVariable Long id) {
		return ResponseEntity.ok(enrollmentService.get(id));
	}

	@PostMapping
	public ResponseEntity<EnrollmentResponse> create(@Valid @RequestBody EnrollmentCreateRequest req) {
		return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.create(req));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<EnrollmentResponse> update(@PathVariable Long id, @Valid @RequestBody EnrollmentUpdateRequest req) {
		return ResponseEntity.ok(enrollmentService.update(id, req));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		enrollmentService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
