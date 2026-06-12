package com.campustid.campus_tid.services;

import com.campustid.campus_tid.models.DirectMessageEntity;
import com.campustid.campus_tid.models.dto.DirectMessageCreateRequest;
import com.campustid.campus_tid.models.dto.DirectMessageResponse;
import com.campustid.campus_tid.models.dto.UserResponse;
import com.campustid.campus_tid.repositories.DirectMessageRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DirectMessageService {

	private final DirectMessageRepository directMessageRepository;
	private final UserService userService;

	public DirectMessageService(DirectMessageRepository directMessageRepository, UserService userService) {
		this.directMessageRepository = directMessageRepository;
		this.userService = userService;
	}

	@Transactional(readOnly = true)
	public List<UserResponse> contacts(Long userId) {
		return userService.list().stream().filter(user -> !user.id().equals(userId)).toList();
	}

	@Transactional(readOnly = true)
	public List<DirectMessageResponse> conversation(Long userId, Long contactId) {
		return directMessageRepository.findConversation(userId, contactId).stream()
			.map(DirectMessageService::toResponse)
			.toList();
	}

	@Transactional
	public DirectMessageResponse send(DirectMessageCreateRequest req) {
		var message = new DirectMessageEntity();
		message.setSender(userService.findEntity(req.senderId()));
		message.setReceiver(userService.findEntity(req.receiverId()));
		message.setContent(req.content());
		return toResponse(directMessageRepository.save(message));
	}

	private static DirectMessageResponse toResponse(DirectMessageEntity message) {
		return new DirectMessageResponse(
			message.getId(),
			message.getSender().getId(),
			message.getReceiver().getId(),
			message.getContent(),
			message.getSentAt()
		);
	}
}
