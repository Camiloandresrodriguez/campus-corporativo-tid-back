package com.campustid.campus_tid.models.dto;

import java.util.List;

public record DashboardMetricsResponse(
	long totalUsuarios,
	long totalCursos,
	long totalInscripciones,
	long inscripcionesActivas,
	long inscripcionesCompletadas,
	List<DashboardCategoryMetric> categorias,
	List<CourseResponse> cursosMasInscritos
) {
	public record DashboardCategoryMetric(Long id, String nombre, long totalCursos, long totalInscritos) {}
}
