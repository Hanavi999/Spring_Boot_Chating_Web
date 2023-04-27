package com.example.randomchat.controller;

import com.example.randomchat.Repository.ChatMessageRepository;
import com.example.randomchat.adapter.MemberAdapter;
import com.example.randomchat.entity.ChatMessage;
import com.example.randomchat.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;

    private final ChatMessageRepository chatMessageRepository;

    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        if(ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender()+"님이 입장하였습니다.");
        } else {
            System.out.println("메시지 : " + message.getMessage() + " 시간 : " + message.getTime());
            chatMessageRepository.save(message);

        }

        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }

    @GetMapping("/chat/username")
    public String getName(@AuthenticationPrincipal MemberAdapter memberAdapter) {
        String username = memberAdapter.getMember().getNickname();
        return "{\"username\": \"" + username + "\"}";
    }
}
