package com.campustid.campus_tid.controllers;

import com.campustid.campus_tid.models.dto.GradeResponse;
import com.campustid.campus_tid.models.dto.GradeUpsertRequest;
import com.campustid.campus_tid.services.GradeService;
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
@RequestMapping("/api/grades")
public class GradesController {

	private final GradeService gradeService;

	public GradesController(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	@GetMapping
	public ResponseEntity<List<GradeResponse>> list(@RequestParam(required = false) Long userId) {
		return ResponseEntity.ok(gradeService.list(userId));
	}

	@GetMapping("/{id}")
	public ResponseEntity<GradeResponse> get(@PathVariable Long id) {
		return ResponseEntity.ok(gradeService.get(id));
	}

	@PostMapping
	public ResponseEntity<GradeResponse> create(@Valid @RequestBody GradeUpsertRequest req) {
		return ResponseEntity.status(HttpStatus.CREATED).body(gradeService.create(req));
	}

	@PutMapping("/{id}")
	public ResponseEntity<GradeResponse> update(@PathVariable Long id, @Valid @RequestBody GradeUpsertRequest req) {
		return ResponseEntity.ok(gradeService.update(id, req));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		gradeService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
