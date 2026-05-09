package com.campustid.campus_tid.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.Instant;

@Entity
@Table(
	name = "enrollments",
	uniqueConstraints = {
		@UniqueConstraint(name = "uk_enrollments_user_course", columnNames = { "userId", "courseId" })
	}
)
public class EnrollmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userId", nullable = false)
	private UserEntity user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "courseId", nullable = false)
	private CourseEntity course;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private EnrollmentStatus status;

	@Column(nullable = false)
	private Instant enrolledAt;

	@PrePersist
	void prePersist() {
		this.enrolledAt = Instant.now();
		if (this.status == null) {
			this.status = EnrollmentStatus.INSCRITO;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public CourseEntity getCourse() {
		return course;
	}

	public void setCourse(CourseEntity course) {
		this.course = course;
	}

	public EnrollmentStatus getStatus() {
		return status;
	}

	public void setStatus(EnrollmentStatus status) {
		this.status = status;
	}

	public Instant getEnrolledAt() {
		return enrolledAt;
	}
}
