package com.campustid.campus_tid.services;

import com.campustid.campus_tid.exception.NotFoundException;
import com.campustid.campus_tid.models.AnnouncementReadEntity;
import com.campustid.campus_tid.models.AnnouncementEntity;
import com.campustid.campus_tid.models.dto.AnnouncementResponse;
import com.campustid.campus_tid.models.dto.AnnouncementUpsertRequest;
import com.campustid.campus_tid.repositories.AnnouncementReadRepository;
import com.campustid.campus_tid.repositories.AnnouncementRepository;
import com.campustid.campus_tid.services.UserService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnnouncementService {

	private final AnnouncementRepository announcementRepository;
	private final AnnouncementReadRepository announcementReadRepository;
	private final UserService userService;

	public AnnouncementService(
		AnnouncementRepository announcementRepository,
		AnnouncementReadRepository announcementReadRepository,
		UserService userService
	) {
		this.announcementRepository = announcementRepository;
		this.announcementReadRepository = announcementReadRepository;
		this.userService = userService;
	}

	@Transactional
	public AnnouncementResponse create(AnnouncementUpsertRequest req) {
		var a = new AnnouncementEntity();
		apply(a, req);
		return toResponse(announcementRepository.save(a), false);
	}

	@Transactional(readOnly = true)
	public List<AnnouncementResponse> list(Long userId) {
		Set<Long> readIds = userId == null ? Set.of() : announcementReadRepository.findByUserId(userId).stream()
			.map(r -> r.getAnnouncement().getId())
			.collect(Collectors.toSet());
		return announcementRepository.findAll().stream()
			.map(a -> toResponse(a, readIds.contains(a.getId())))
			.toList();
	}

	@Transactional(readOnly = true)
	public AnnouncementResponse get(Long id, Long userId) {
		var read = userId != null && announcementReadRepository.existsByUserIdAndAnnouncementId(userId, id);
		return toResponse(findEntity(id), read);
	}

	@Transactional
	public AnnouncementResponse update(Long id, AnnouncementUpsertRequest req) {
		var a = findEntity(id);
		apply(a, req);
		return toResponse(announcementRepository.save(a), false);
	}

	@Transactional
	public void delete(Long id) {
		if (!announcementRepository.existsById(id)) {
			throw new NotFoundException("Anuncio no encontrado");
		}
		announcementRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public AnnouncementEntity findEntity(Long id) {
		return announcementRepository.findById(id).orElseThrow(() -> new NotFoundException("Anuncio no encontrado"));
	}

	@Transactional
	public void markRead(Long id, Long userId) {
		if (announcementReadRepository.existsByUserIdAndAnnouncementId(userId, id)) {
			return;
		}
		var read = new AnnouncementReadEntity();
		read.setAnnouncement(findEntity(id));
		read.setUser(userService.findEntity(userId));
		announcementReadRepository.save(read);
	}

	private void apply(AnnouncementEntity a, AnnouncementUpsertRequest req) {
		a.setTitle(req.title());
		a.setContent(req.content());
		a.setDate(req.date());
		a.setPriority(req.priority() == null || req.priority().isBlank() ? "Media" : req.priority());
		a.setAuthor(req.author() == null || req.author().isBlank() ? "Administración" : req.author());
		a.setInfoUrl(req.infoUrl());
	}

	public static AnnouncementResponse toResponse(AnnouncementEntity a, boolean read) {
		return new AnnouncementResponse(
			a.getId(),
			a.getTitle(),
			a.getContent(),
			a.getDate(),
			a.getCreatedAt(),
			a.getPriority(),
			a.getAuthor(),
			a.getInfoUrl(),
			read
		);
	}
}
