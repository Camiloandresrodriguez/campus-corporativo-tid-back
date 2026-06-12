package com.campustid.campus_tid.repositories;

import com.campustid.campus_tid.models.DirectMessageEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DirectMessageRepository extends JpaRepository<DirectMessageEntity, Long> {
	@Query("""
		select m from DirectMessageEntity m
		where (m.sender.id = :userId and m.receiver.id = :contactId)
		   or (m.sender.id = :contactId and m.receiver.id = :userId)
		order by m.sentAt asc
		""")
	List<DirectMessageEntity> findConversation(@Param("userId") Long userId, @Param("contactId") Long contactId);
}
