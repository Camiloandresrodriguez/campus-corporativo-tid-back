package com.campustid.campus_tid.controllers;

import com.campustid.campus_tid.models.dto.AttendanceResponse;
import com.campustid.campus_tid.models.dto.AttendanceUpsertRequest;
import com.campustid.campus_tid.services.AttendanceService;
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
@RequestMapping("/api/attendance")
public class AttendanceController {

	private final AttendanceService attendanceService;

	public AttendanceController(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}

	@GetMapping
	public ResponseEntity<List<AttendanceResponse>> list(@RequestParam(required = false) Long userId) {
		return ResponseEntity.ok(attendanceService.list(userId));
	}

	@GetMapping("/{id}")
	public ResponseEntity<AttendanceResponse> get(@PathVariable Long id) {
		return ResponseEntity.ok(attendanceService.get(id));
	}

	@PostMapping
	public ResponseEntity<AttendanceResponse> create(@Valid @RequestBody AttendanceUpsertRequest req) {
		return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.create(req));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AttendanceResponse> update(@PathVariable Long id, @Valid @RequestBody AttendanceUpsertRequest req) {
		return ResponseEntity.ok(attendanceService.update(id, req));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		attendanceService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
