package com.campustid.campus_tid.controllers;

import com.campustid.campus_tid.models.dto.DashboardMetricsResponse;
import com.campustid.campus_tid.services.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

	private final DashboardService dashboardService;

	public DashboardController(DashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}

	@GetMapping("/metrics")
	public ResponseEntity<DashboardMetricsResponse> metrics() {
		return ResponseEntity.ok(dashboardService.metrics());
	}
}
