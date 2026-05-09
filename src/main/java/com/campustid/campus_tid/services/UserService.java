package com.campustid.campus_tid.services;

import com.campustid.campus_tid.exception.BadRequestException;
import com.campustid.campus_tid.exception.ConflictException;
import com.campustid.campus_tid.exception.NotFoundException;
import com.campustid.campus_tid.exception.UnauthorizedException;
import com.campustid.campus_tid.models.UserEntity;
import com.campustid.campus_tid.models.dto.ChangePasswordRequest;
import com.campustid.campus_tid.models.dto.UserCreateRequest;
import com.campustid.campus_tid.models.dto.UserResponse;
import com.campustid.campus_tid.models.dto.UserUpdateRequest;
import com.campustid.campus_tid.repositories.UserRepository;
import com.campustid.campus_tid.util.PasswordHasher;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public UserResponse create(UserCreateRequest req) {
		if (!req.password().equals(req.confirmPassword())) {
			throw new BadRequestException("La confirmación de contraseña no coincide");
		}
		if (userRepository.existsByEmailIgnoreCase(req.email())) {
			throw new ConflictException("El correo ya está registrado");
		}
		var u = new UserEntity();
		u.setFirstName(req.firstName());
		u.setLastName(req.lastName());
		u.setEmail(req.email().trim().toLowerCase());
		u.setPasswordHash(PasswordHasher.hash(req.password()));
		u.setPhone(req.phone());
		u.setDocument(req.document());
		u.setAddress(req.address());
		u.setRole("STUDENT");
		return toResponse(userRepository.save(u));
	}

	@Transactional(readOnly = true)
	public List<UserResponse> list() {
		return userRepository.findAll().stream().map(UserService::toResponse).toList();
	}

	@Transactional(readOnly = true)
	public UserResponse get(Long id) {
		return toResponse(findEntity(id));
	}

	@Transactional
	public UserResponse update(Long id, UserUpdateRequest req) {
		var u = findEntity(id);
		if (req.firstName() != null) {
			u.setFirstName(req.firstName());
		}
		if (req.lastName() != null) {
			u.setLastName(req.lastName());
		}
		if (req.phone() != null) {
			u.setPhone(req.phone());
		}
		if (req.document() != null) {
			u.setDocument(req.document());
		}
		if (req.address() != null) {
			u.setAddress(req.address());
		}
		if (req.role() != null) {
			u.setRole(req.role());
		}
		return toResponse(userRepository.save(u));
	}

	@Transactional
	public void delete(Long id) {
		if (!userRepository.existsById(id)) {
			throw new NotFoundException("Usuario no encontrado");
		}
		userRepository.deleteById(id);
	}

	@Transactional
	public void changePassword(Long id, ChangePasswordRequest req) {
		if (!req.newPassword().equals(req.confirmNewPassword())) {
			throw new BadRequestException("La confirmación de contraseña no coincide");
		}
		var u = findEntity(id);
		if (!PasswordHasher.matches(req.currentPassword(), u.getPasswordHash())) {
			throw new UnauthorizedException("Contraseña actual incorrecta");
		}
		u.setPasswordHash(PasswordHasher.hash(req.newPassword()));
		userRepository.save(u);
	}

	@Transactional(readOnly = true)
	public UserEntity findEntity(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
	}

	public static UserResponse toResponse(UserEntity u) {
		return new UserResponse(
			u.getId(),
			u.getFirstName(),
			u.getLastName(),
			u.getEmail(),
			u.getPhone(),
			u.getDocument(),
			u.getAddress(),
			u.getRole(),
			u.getCreatedAt()
		);
	}
}
