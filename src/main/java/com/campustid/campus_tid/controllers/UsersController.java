package com.campustid.campus_tid.controllers;

import com.campustid.campus_tid.models.dto.ApiMessageResponse;
import com.campustid.campus_tid.models.dto.ChangePasswordRequest;
import com.campustid.campus_tid.models.dto.UserCreateRequest;
import com.campustid.campus_tid.models.dto.UserResponse;
import com.campustid.campus_tid.models.dto.UserUpdateRequest;
import com.campustid.campus_tid.services.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	private final UserService userService;

	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<UserResponse>> list() {
		return ResponseEntity.ok(userService.list());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> get(@PathVariable Long id) {
		return ResponseEntity.ok(userService.get(id));
	}

	@PostMapping
	public ResponseEntity<UserResponse> create(@Valid @RequestBody UserCreateRequest req) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(req));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UserResponse> update(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest req) {
		return ResponseEntity.ok(userService.update(id, req));
	}

	@PostMapping("/{id}/change-password")
	public ResponseEntity<ApiMessageResponse> changePassword(
		@PathVariable Long id,
		@Valid @RequestBody ChangePasswordRequest req
	) {
		userService.changePassword(id, req);
		return ResponseEntity.ok(new ApiMessageResponse("Contraseña actualizada"));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
