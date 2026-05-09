package com.campustid.campus_tid.services;

import com.campustid.campus_tid.exception.NotFoundException;
import com.campustid.campus_tid.models.GradeEntity;
import com.campustid.campus_tid.models.dto.GradeResponse;
import com.campustid.campus_tid.models.dto.GradeUpsertRequest;
import com.campustid.campus_tid.repositories.GradeRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GradeService {

	private final GradeRepository gradeRepository;
	private final UserService userService;
	private final CourseService courseService;

	public GradeService(GradeRepository gradeRepository, UserService userService, CourseService courseService) {
		this.gradeRepository = gradeRepository;
		this.userService = userService;
		this.courseService = courseService;
	}

	@Transactional
	public GradeResponse create(GradeUpsertRequest req) {
		var g = new GradeEntity();
		apply(g, req);
		return toResponse(gradeRepository.save(g));
	}

	@Transactional(readOnly = true)
	public List<GradeResponse> list(Long userId) {
		var list = userId == null ? gradeRepository.findAll() : gradeRepository.findByUserId(userId);
		return list.stream().map(GradeService::toResponse).toList();
	}

	@Transactional(readOnly = true)
	public GradeResponse get(Long id) {
		return toResponse(findEntity(id));
	}

	@Transactional
	public GradeResponse update(Long id, GradeUpsertRequest req) {
		var g = findEntity(id);
		apply(g, req);
		return toResponse(gradeRepository.save(g));
	}

	@Transactional
	public void delete(Long id) {
		if (!gradeRepository.existsById(id)) {
			throw new NotFoundException("Calificación no encontrada");
		}
		gradeRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public GradeEntity findEntity(Long id) {
		return gradeRepository.findById(id).orElseThrow(() -> new NotFoundException("Calificación no encontrada"));
	}

	private void apply(GradeEntity g, GradeUpsertRequest req) {
		g.setUser(userService.findEntity(req.userId()));
		g.setCourse(courseService.findEntity(req.courseId()));
		g.setScore(req.score());
		g.setNote(req.note());
		g.setDate(req.date() == null ? LocalDate.now() : req.date());
	}

	public static GradeResponse toResponse(GradeEntity g) {
		return new GradeResponse(
			g.getId(),
			g.getUser().getId(),
			g.getCourse().getId(),
			g.getCourse().getTitle(),
			g.getScore(),
			g.getNote(),
			g.getDate()
		);
	}
}
