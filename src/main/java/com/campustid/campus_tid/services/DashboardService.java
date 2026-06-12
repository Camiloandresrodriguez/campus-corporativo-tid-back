package com.campustid.campus_tid.services;

import com.campustid.campus_tid.models.EnrollmentStatus;
import com.campustid.campus_tid.models.dto.DashboardMetricsResponse;
import com.campustid.campus_tid.models.dto.DashboardMetricsResponse.DashboardCategoryMetric;
import com.campustid.campus_tid.repositories.CategoryRepository;
import com.campustid.campus_tid.repositories.CourseRepository;
import com.campustid.campus_tid.repositories.EnrollmentRepository;
import com.campustid.campus_tid.repositories.UserRepository;
import java.util.Comparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DashboardService {

	private final UserRepository userRepository;
	private final CourseRepository courseRepository;
	private final EnrollmentRepository enrollmentRepository;
	private final CategoryRepository categoryRepository;

	public DashboardService(
		UserRepository userRepository,
		CourseRepository courseRepository,
		EnrollmentRepository enrollmentRepository,
		CategoryRepository categoryRepository
	) {
		this.userRepository = userRepository;
		this.courseRepository = courseRepository;
		this.enrollmentRepository = enrollmentRepository;
		this.categoryRepository = categoryRepository;
	}

	@Transactional(readOnly = true)
	public DashboardMetricsResponse metrics() {
		var courses = courseRepository.findAll();
		var enrollments = enrollmentRepository.findAll();
		var categories = categoryRepository.findAll();

		var activeEnrollments = enrollments.stream()
			.filter(e -> e.getStatus() == EnrollmentStatus.INSCRITO)
			.count();
		var completedEnrollments = enrollments.stream()
			.filter(e -> e.getStatus() == EnrollmentStatus.COMPLETADO)
			.count();

		var categoryMetrics = categories.stream()
			.map(category -> {
				var categoryCourses = courses.stream()
					.filter(course -> course.getCategory().getId().equals(category.getId()))
					.toList();
				var courseIds = categoryCourses.stream().map(course -> course.getId()).toList();
				var enrollmentCount = enrollments.stream()
					.filter(enrollment -> courseIds.contains(enrollment.getCourse().getId()))
					.count();

				return new DashboardCategoryMetric(
					category.getId(),
					category.getName(),
					categoryCourses.size(),
					enrollmentCount
				);
			})
			.toList();

		var topCourses = courses.stream()
			.sorted(Comparator.comparingLong(course -> -enrollments.stream()
				.filter(enrollment -> enrollment.getCourse().getId().equals(course.getId()))
				.count()))
			.limit(5)
			.map(course -> CourseService.toResponse(
				course,
				enrollments.stream()
					.filter(enrollment -> enrollment.getCourse().getId().equals(course.getId()))
					.count()
			))
			.toList();

		return new DashboardMetricsResponse(
			userRepository.count(),
			courses.size(),
			enrollments.size(),
			activeEnrollments,
			completedEnrollments,
			categoryMetrics,
			topCourses
		);
	}
}
