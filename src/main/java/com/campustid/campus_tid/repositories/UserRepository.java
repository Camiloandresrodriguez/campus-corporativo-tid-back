package com.campustid.campus_tid.repositories;

import com.campustid.campus_tid.models.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByEmailIgnoreCase(String email);

	boolean existsByEmailIgnoreCase(String email);
}
