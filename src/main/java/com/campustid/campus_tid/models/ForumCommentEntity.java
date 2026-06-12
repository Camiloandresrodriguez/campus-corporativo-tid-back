package com.campustid.campus_tid.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "forum_comments")
public class ForumCommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "thread_id", nullable = false)
	private ForumThreadEntity thread;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "author_id", nullable = false)
	private UserEntity author;

	@Column(nullable = false, length = 4000)
	private String content;

	@Column(nullable = false)
	private Instant createdAt;

	@PrePersist
	void prePersist() {
		this.createdAt = Instant.now();
	}

	public Long getId() {
		return id;
	}

	public ForumThreadEntity getThread() {
		return thread;
	}

	public void setThread(ForumThreadEntity thread) {
		this.thread = thread;
	}

	public UserEntity getAuthor() {
		return author;
	}

	public void setAuthor(UserEntity author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}
}
