package com.campustid.campus_tid.services;

import com.campustid.campus_tid.exception.NotFoundException;
import com.campustid.campus_tid.models.CategoryEntity;
import com.campustid.campus_tid.models.dto.CategoryResponse;
import com.campustid.campus_tid.models.dto.CategoryUpsertRequest;
import com.campustid.campus_tid.repositories.CategoryRepository;
import com.campustid.campus_tid.repositories.CourseRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final CourseRepository courseRepository;

	public CategoryService(CategoryRepository categoryRepository, CourseRepository courseRepository) {
		this.categoryRepository = categoryRepository;
		this.courseRepository = courseRepository;
	}

	@Transactional(readOnly = true)
	public List<CategoryResponse> list(boolean withCounts) {
		return categoryRepository.findAll().stream()
			.map(c -> new CategoryResponse(c.getId(), c.getName(), withCounts ? courseRepository.countByCategoryId(c.getId()) : null))
			.toList();
	}

	@Transactional(readOnly = true)
	public CategoryResponse get(Long id) {
		var c = findEntity(id);
		return new CategoryResponse(c.getId(), c.getName(), courseRepository.countByCategoryId(c.getId()));
	}

	@Transactional
	public CategoryResponse create(CategoryUpsertRequest req) {
		var c = new CategoryEntity();
		c.setName(req.name());
		c = categoryRepository.save(c);
		return new CategoryResponse(c.getId(), c.getName(), 0L);
	}

	@Transactional
	public CategoryResponse update(Long id, CategoryUpsertRequest req) {
		var c = findEntity(id);
		c.setName(req.name());
		c = categoryRepository.save(c);
		return new CategoryResponse(c.getId(), c.getName(), courseRepository.countByCategoryId(c.getId()));
	}

	@Transactional
	public void delete(Long id) {
		if (!categoryRepository.existsById(id)) {
			throw new NotFoundException("Categoría no encontrada");
		}
		categoryRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public CategoryEntity findEntity(Long id) {
		return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
	}
}
