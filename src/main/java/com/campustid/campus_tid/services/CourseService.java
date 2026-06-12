package com.campustid.campus_tid.services;

import com.campustid.campus_tid.exception.NotFoundException;
import com.campustid.campus_tid.models.CourseEntity;
import com.campustid.campus_tid.models.dto.CourseResponse;
import com.campustid.campus_tid.models.dto.CourseUpsertRequest;
import com.campustid.campus_tid.repositories.CourseRepository;
import com.campustid.campus_tid.repositories.EnrollmentRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {

	private final CourseRepository courseRepository;
	private final CategoryService categoryService;
	private final EnrollmentRepository enrollmentRepository;

	public CourseService(
		CourseRepository courseRepository,
		CategoryService categoryService,
		EnrollmentRepository enrollmentRepository
	) {
		this.courseRepository = courseRepository;
		this.categoryService = categoryService;
		this.enrollmentRepository = enrollmentRepository;
	}

	@Transactional(readOnly = true)
	public List<CourseResponse> list(Long categoryId) {
		var courses = categoryId == null ? courseRepository.findAll() : courseRepository.findByCategoryId(categoryId);
		return courses.stream().map(this::toResponseWithCounts).toList();
	}

	@Transactional(readOnly = true)
	public CourseResponse get(Long id) {
		return toResponseWithCounts(findEntity(id));
	}

	@Transactional
	public CourseResponse create(CourseUpsertRequest req) {
		var c = new CourseEntity();
		apply(c, req);
		return toResponseWithCounts(courseRepository.save(c));
	}

	@Transactional
	public CourseResponse update(Long id, CourseUpsertRequest req) {
		var c = findEntity(id);
		apply(c, req);
		return toResponseWithCounts(courseRepository.save(c));
	}

	@Transactional
	public void delete(Long id) {
		if (!courseRepository.existsById(id)) {
			throw new NotFoundException("Curso no encontrado");
		}
		courseRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public CourseEntity findEntity(Long id) {
		return courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Curso no encontrado"));
	}

	private void apply(CourseEntity c, CourseUpsertRequest req) {
		c.setTitle(req.title());
		c.setDescription(req.description());
		c.setCategory(categoryService.findEntity(req.categoryId()));
		c.setStartDate(req.startDate());
		c.setEndDate(req.endDate());
		c.setCapacity(req.capacity());
		if (req.active() != null) {
			c.setActive(req.active());
		}
	}

	private CourseResponse toResponseWithCounts(CourseEntity c) {
		return toResponse(c, enrollmentRepository.countByCourseId(c.getId()));
	}

	public static CourseResponse toResponse(CourseEntity c) {
		return toResponse(c, null);
	}

	public static CourseResponse toResponse(CourseEntity c, Long enrolledCount) {
		return new CourseResponse(
			c.getId(),
			c.getTitle(),
			c.getDescription(),
			c.getCategory().getId(),
			c.getCategory().getName(),
			c.getStartDate(),
			c.getEndDate(),
			c.getCapacity(),
			c.isActive(),
			enrolledCount
		);
	}
}
