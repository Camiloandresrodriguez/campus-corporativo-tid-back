package com.campustid.campus_tid.controllers;

import com.campustid.campus_tid.models.dto.CourseResponse;
import com.campustid.campus_tid.models.dto.CourseUpsertRequest;
import com.campustid.campus_tid.services.CourseService;
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
@RequestMapping("/api/courses")
public class CoursesController {

	private final CourseService courseService;

	public CoursesController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping
	public ResponseEntity<List<CourseResponse>> list(@RequestParam(required = false) Long categoryId) {
		return ResponseEntity.ok(courseService.list(categoryId));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CourseResponse> get(@PathVariable Long id) {
		return ResponseEntity.ok(courseService.get(id));
	}

	@PostMapping
	public ResponseEntity<CourseResponse> create(@Valid @RequestBody CourseUpsertRequest req) {
		return ResponseEntity.status(HttpStatus.CREATED).body(courseService.create(req));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CourseResponse> update(@PathVariable Long id, @Valid @RequestBody CourseUpsertRequest req) {
		return ResponseEntity.ok(courseService.update(id, req));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		courseService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
