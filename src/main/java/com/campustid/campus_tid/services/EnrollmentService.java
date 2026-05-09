package com.campustid.campus_tid.services;

import com.campustid.campus_tid.exception.BadRequestException;
import com.campustid.campus_tid.exception.ConflictException;
import com.campustid.campus_tid.exception.NotFoundException;
import com.campustid.campus_tid.models.EnrollmentEntity;
import com.campustid.campus_tid.models.EnrollmentStatus;
import com.campustid.campus_tid.models.dto.EnrollmentCreateRequest;
import com.campustid.campus_tid.models.dto.EnrollmentResponse;
import com.campustid.campus_tid.models.dto.EnrollmentUpdateRequest;
import com.campustid.campus_tid.repositories.EnrollmentRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {

	private final EnrollmentRepository enrollmentRepository;
	private final UserService userService;
	private final CourseService courseService;

	public EnrollmentService(
		EnrollmentRepository enrollmentRepository,
		UserService userService,
		CourseService courseService
	) {
		this.enrollmentRepository = enrollmentRepository;
		this.userService = userService;
		this.courseService = courseService;
	}

	@Transactional
	public EnrollmentResponse create(EnrollmentCreateRequest req) {
		if (enrollmentRepository.findByUserIdAndCourseId(req.userId(), req.courseId()).isPresent()) {
			throw new ConflictException("El usuario ya está inscrito en este curso");
		}
		var e = new EnrollmentEntity();
		e.setUser(userService.findEntity(req.userId()));
		e.setCourse(courseService.findEntity(req.courseId()));
		e.setStatus(EnrollmentStatus.INSCRITO);
		return toResponse(enrollmentRepository.save(e));
	}

	@Transactional(readOnly = true)
	public List<EnrollmentResponse> list(Long userId) {
		var list = userId == null ? enrollmentRepository.findAll() : enrollmentRepository.findByUserId(userId);
		return list.stream().map(EnrollmentService::toResponse).toList();
	}

	@Transactional(readOnly = true)
	public EnrollmentResponse get(Long id) {
		return toResponse(findEntity(id));
	}

	@Transactional
	public EnrollmentResponse update(Long id, EnrollmentUpdateRequest req) {
		var e = findEntity(id);
		e.setStatus(parseStatus(req.status()));
		return toResponse(enrollmentRepository.save(e));
	}

	@Transactional
	public void delete(Long id) {
		if (!enrollmentRepository.existsById(id)) {
			throw new NotFoundException("Inscripción no encontrada");
		}
		enrollmentRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public EnrollmentEntity findEntity(Long id) {
		return enrollmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Inscripción no encontrada"));
	}

	private EnrollmentStatus parseStatus(String status) {
		try {
			return EnrollmentStatus.valueOf(status.trim().toUpperCase());
		} catch (Exception ex) {
			throw new BadRequestException("Estado de inscripción inválido");
		}
	}

	public static EnrollmentResponse toResponse(EnrollmentEntity e) {
		return new EnrollmentResponse(
			e.getId(),
			e.getUser().getId(),
			e.getCourse().getId(),
			e.getCourse().getTitle(),
			e.getStatus().name(),
			e.getEnrolledAt()
		);
	}
}
