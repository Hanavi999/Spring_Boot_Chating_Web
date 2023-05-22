package com.example.randomchat.Repository;


import com.example.randomchat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByRoomId(String roomId);

    Optional<ChatMessage> findTopByOrderByIdDesc();

}
