package com.campustid.campus_tid.controllers;

import com.campustid.campus_tid.models.dto.ApiMessageResponse;
import com.campustid.campus_tid.models.dto.LoginResponse;
import com.campustid.campus_tid.models.dto.RecoverRequest;
import com.campustid.campus_tid.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@GetMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestParam String email, @RequestParam String password) {
		return ResponseEntity.ok(authService.login(email, password));
	}

	@PostMapping("/recover")
	public ResponseEntity<ApiMessageResponse> recover(@Valid @RequestBody RecoverRequest req) {
		return ResponseEntity.ok(new ApiMessageResponse("Si el correo existe, recibirás instrucciones."));
	}
}
