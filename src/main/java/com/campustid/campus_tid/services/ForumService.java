package com.campustid.campus_tid.services;

import com.campustid.campus_tid.exception.NotFoundException;
import com.campustid.campus_tid.models.ForumCommentEntity;
import com.campustid.campus_tid.models.ForumThreadEntity;
import com.campustid.campus_tid.models.UserEntity;
import com.campustid.campus_tid.models.dto.ForumCommentCreateRequest;
import com.campustid.campus_tid.models.dto.ForumCommentResponse;
import com.campustid.campus_tid.models.dto.ForumThreadCreateRequest;
import com.campustid.campus_tid.models.dto.ForumThreadResponse;
import com.campustid.campus_tid.repositories.ForumCommentRepository;
import com.campustid.campus_tid.repositories.ForumThreadRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ForumService {

	private final ForumThreadRepository forumThreadRepository;
	private final ForumCommentRepository forumCommentRepository;
	private final UserService userService;

	public ForumService(
		ForumThreadRepository forumThreadRepository,
		ForumCommentRepository forumCommentRepository,
		UserService userService
	) {
		this.forumThreadRepository = forumThreadRepository;
		this.forumCommentRepository = forumCommentRepository;
		this.userService = userService;
	}

	@Transactional(readOnly = true)
	public List<ForumThreadResponse> list() {
		return forumThreadRepository.findAllByOrderByCreatedAtDesc().stream().map(this::toResponse).toList();
	}

	@Transactional(readOnly = true)
	public ForumThreadResponse get(Long id) {
		return toResponse(findThread(id));
	}

	@Transactional
	public ForumThreadResponse create(ForumThreadCreateRequest req) {
		var author = userService.findEntity(req.authorId());
		var thread = new ForumThreadEntity();
		thread.setTitle(req.title());
		thread.setCategory(req.category());
		thread.setAuthor(author);
		var saved = forumThreadRepository.save(thread);

		var comment = new ForumCommentEntity();
		comment.setThread(saved);
		comment.setAuthor(author);
		comment.setContent(req.content());
		forumCommentRepository.save(comment);

		return toResponse(saved);
	}

	@Transactional
	public ForumThreadResponse vote(Long id) {
		var thread = findThread(id);
		thread.setVotes(thread.getVotes() + 1);
		return toResponse(forumThreadRepository.save(thread));
	}

	@Transactional
	public ForumThreadResponse comment(Long id, ForumCommentCreateRequest req) {
		var comment = new ForumCommentEntity();
		comment.setThread(findThread(id));
		comment.setAuthor(userService.findEntity(req.authorId()));
		comment.setContent(req.content());
		forumCommentRepository.save(comment);
		return get(id);
	}

	private ForumThreadEntity findThread(Long id) {
		return forumThreadRepository.findById(id).orElseThrow(() -> new NotFoundException("Foro no encontrado"));
	}

	private ForumThreadResponse toResponse(ForumThreadEntity thread) {
		var comments = forumCommentRepository.findByThreadIdOrderByCreatedAtAsc(thread.getId()).stream()
			.map(ForumService::toCommentResponse)
			.toList();
		return new ForumThreadResponse(
			thread.getId(),
			thread.getTitle(),
			thread.getCategory(),
			thread.getAuthor().getId(),
			fullName(thread.getAuthor()),
			thread.getVotes(),
			comments.size(),
			thread.getCreatedAt(),
			comments
		);
	}

	private static ForumCommentResponse toCommentResponse(ForumCommentEntity comment) {
		return new ForumCommentResponse(
			comment.getId(),
			comment.getAuthor().getId(),
			fullName(comment.getAuthor()),
			comment.getContent(),
			comment.getCreatedAt()
		);
	}

	private static String fullName(UserEntity user) {
		return (user.getFirstName() + " " + user.getLastName()).trim();
	}
}
