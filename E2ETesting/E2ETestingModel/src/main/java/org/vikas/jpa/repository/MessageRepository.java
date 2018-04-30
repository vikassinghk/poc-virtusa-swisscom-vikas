package org.vikas.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.vikas.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "SELECT * FROM MESSAGES WHERE USER_ID = ?", nativeQuery = true)
    List<Message> findByUserId(String userId);
}
