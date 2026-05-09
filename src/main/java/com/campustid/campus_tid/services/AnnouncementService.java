package com.campustid.campus_tid.services;

import com.campustid.campus_tid.exception.NotFoundException;
import com.campustid.campus_tid.models.AnnouncementEntity;
import com.campustid.campus_tid.models.dto.AnnouncementResponse;
import com.campustid.campus_tid.models.dto.AnnouncementUpsertRequest;
import com.campustid.campus_tid.repositories.AnnouncementRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnnouncementService {

	private final AnnouncementRepository announcementRepository;

	public AnnouncementService(AnnouncementRepository announcementRepository) {
		this.announcementRepository = announcementRepository;
	}

	@Transactional
	public AnnouncementResponse create(AnnouncementUpsertRequest req) {
		var a = new AnnouncementEntity();
		apply(a, req);
		return toResponse(announcementRepository.save(a));
	}

	@Transactional(readOnly = true)
	public List<AnnouncementResponse> list() {
		return announcementRepository.findAll().stream().map(AnnouncementService::toResponse).toList();
	}

	@Transactional(readOnly = true)
	public AnnouncementResponse get(Long id) {
		return toResponse(findEntity(id));
	}

	@Transactional
	public AnnouncementResponse update(Long id, AnnouncementUpsertRequest req) {
		var a = findEntity(id);
		apply(a, req);
		return toResponse(announcementRepository.save(a));
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

	private void apply(AnnouncementEntity a, AnnouncementUpsertRequest req) {
		a.setTitle(req.title());
		a.setContent(req.content());
		a.setDate(req.date());
	}

	public static AnnouncementResponse toResponse(AnnouncementEntity a) {
		return new AnnouncementResponse(a.getId(), a.getTitle(), a.getContent(), a.getDate(), a.getCreatedAt());
	}
}
