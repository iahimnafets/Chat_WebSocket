package com.chat.socket.repository;

import com.chat.socket.entitys.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findByFrom(String from);
}
