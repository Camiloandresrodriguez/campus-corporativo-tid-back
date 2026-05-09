package com.campustid.campus_tid.exception;

import com.campustid.campus_tid.models.dto.ApiErrorResponse;
import java.util.HashMap;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
		var fieldErrors = new HashMap<String, String>();
		for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
			fieldErrors.putIfAbsent(fe.getField(), fe.getDefaultMessage());
		}
		return ResponseEntity.badRequest().body(new ApiErrorResponse("Validación fallida", fieldErrors));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiErrorResponse> handleInvalidBody(HttpMessageNotReadableException ex) {
		return ResponseEntity.badRequest().body(new ApiErrorResponse("Body inválido", null));
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiErrorResponse> handleBadRequest(BadRequestException ex) {
		return ResponseEntity.badRequest().body(new ApiErrorResponse(ex.getMessage(), null));
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ApiErrorResponse> handleUnauthorized(UnauthorizedException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiErrorResponse(ex.getMessage(), null));
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleNotFound(NotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorResponse(ex.getMessage(), null));
	}

	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<ApiErrorResponse> handleConflict(ConflictException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiErrorResponse(ex.getMessage(), null));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiErrorResponse> handleIntegrity(DataIntegrityViolationException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiErrorResponse("Conflicto de datos", null));
	}
}
