package com.campustid.campus_tid.controllers;

import com.campustid.campus_tid.models.dto.CategoryResponse;
import com.campustid.campus_tid.models.dto.CategoryUpsertRequest;
import com.campustid.campus_tid.services.CategoryService;
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
@RequestMapping("/api/categories")
public class CategoriesController {

	private final CategoryService categoryService;

	public CategoriesController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<List<CategoryResponse>> list(@RequestParam(required = false, defaultValue = "false") boolean withCounts) {
		return ResponseEntity.ok(categoryService.list(withCounts));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponse> get(@PathVariable Long id) {
		return ResponseEntity.ok(categoryService.get(id));
	}

	@PostMapping
	public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryUpsertRequest req) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(req));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryUpsertRequest req) {
		return ResponseEntity.ok(categoryService.update(id, req));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
