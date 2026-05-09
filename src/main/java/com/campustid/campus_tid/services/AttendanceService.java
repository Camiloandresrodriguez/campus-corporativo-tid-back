package com.campustid.campus_tid.services;

import com.campustid.campus_tid.exception.BadRequestException;
import com.campustid.campus_tid.exception.NotFoundException;
import com.campustid.campus_tid.models.AttendanceEntity;
import com.campustid.campus_tid.models.AttendanceStatus;
import com.campustid.campus_tid.models.dto.AttendanceResponse;
import com.campustid.campus_tid.models.dto.AttendanceUpsertRequest;
import com.campustid.campus_tid.repositories.AttendanceRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttendanceService {

	private final AttendanceRepository attendanceRepository;
	private final UserService userService;
	private final CourseService courseService;

	public AttendanceService(
		AttendanceRepository attendanceRepository,
		UserService userService,
		CourseService courseService
	) {
		this.attendanceRepository = attendanceRepository;
		this.userService = userService;
		this.courseService = courseService;
	}

	@Transactional
	public AttendanceResponse create(AttendanceUpsertRequest req) {
		var a = new AttendanceEntity();
		apply(a, req);
		return toResponse(attendanceRepository.save(a));
	}

	@Transactional(readOnly = true)
	public List<AttendanceResponse> list(Long userId) {
		var list = userId == null ? attendanceRepository.findAll() : attendanceRepository.findByUserId(userId);
		return list.stream().map(AttendanceService::toResponse).toList();
	}

	@Transactional(readOnly = true)
	public AttendanceResponse get(Long id) {
		return toResponse(findEntity(id));
	}

	@Transactional
	public AttendanceResponse update(Long id, AttendanceUpsertRequest req) {
		var a = findEntity(id);
		apply(a, req);
		return toResponse(attendanceRepository.save(a));
	}

	@Transactional
	public void delete(Long id) {
		if (!attendanceRepository.existsById(id)) {
			throw new NotFoundException("Asistencia no encontrada");
		}
		attendanceRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public AttendanceEntity findEntity(Long id) {
		return attendanceRepository.findById(id).orElseThrow(() -> new NotFoundException("Asistencia no encontrada"));
	}

	private void apply(AttendanceEntity a, AttendanceUpsertRequest req) {
		a.setUser(userService.findEntity(req.userId()));
		a.setCourse(courseService.findEntity(req.courseId()));
		a.setDate(req.date());
		a.setStatus(parseStatus(req.status()));
	}

	private AttendanceStatus parseStatus(String status) {
		try {
			return AttendanceStatus.valueOf(status.trim().toUpperCase());
		} catch (Exception ex) {
			throw new BadRequestException("Estado de asistencia inválido");
		}
	}

	public static AttendanceResponse toResponse(AttendanceEntity a) {
		return new AttendanceResponse(
			a.getId(),
			a.getUser().getId(),
			a.getCourse().getId(),
			a.getCourse().getTitle(),
			a.getDate(),
			a.getStatus().name()
		);
	}
}
