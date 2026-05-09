package com.campustid.campus_tid.services;

import com.campustid.campus_tid.models.dto.DashboardMetricsResponse;
import com.campustid.campus_tid.repositories.CourseRepository;
import com.campustid.campus_tid.repositories.EnrollmentRepository;
import com.campustid.campus_tid.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DashboardService {

	private final UserRepository userRepository;
	private final CourseRepository courseRepository;
	private final EnrollmentRepository enrollmentRepository;

	public DashboardService(
		UserRepository userRepository,
		CourseRepository courseRepository,
		EnrollmentRepository enrollmentRepository
	) {
		this.userRepository = userRepository;
		this.courseRepository = courseRepository;
		this.enrollmentRepository = enrollmentRepository;
	}

	@Transactional(readOnly = true)
	public DashboardMetricsResponse metrics() {
		return new DashboardMetricsResponse(userRepository.count(), courseRepository.count(), enrollmentRepository.count());
	}
}
