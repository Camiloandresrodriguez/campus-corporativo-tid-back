package com.campustid.campus_tid.services;

import com.campustid.campus_tid.exception.UnauthorizedException;
import com.campustid.campus_tid.models.dto.LoginResponse;
import com.campustid.campus_tid.models.dto.UserResponse;
import com.campustid.campus_tid.repositories.UserRepository;
import com.campustid.campus_tid.util.PasswordHasher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

	private final UserRepository userRepository;

	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional(readOnly = true)
	public LoginResponse login(String email, String password) {
		var user = userRepository.findByEmailIgnoreCase(email == null ? null : email.trim())
			.orElseThrow(() -> new UnauthorizedException("Credenciales inválidas"));
		if (!PasswordHasher.matches(password == null ? "" : password, user.getPasswordHash())) {
			throw new UnauthorizedException("Credenciales inválidas");
		}
		UserResponse u = UserService.toResponse(user);
		return new LoginResponse(u);
	}
}
