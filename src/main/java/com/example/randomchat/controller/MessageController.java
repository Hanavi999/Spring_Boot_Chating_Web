package com.example.randomchat.controller;

import com.example.randomchat.Repository.ChatMessageRepository;
import com.example.randomchat.adapter.MemberAdapter;
import com.example.randomchat.entity.ChatMessage;
import com.example.randomchat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;

    private final ChatMessageRepository chatMessageRepository;

    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하였습니다.");
        } else {
            System.out.println("메시지 : " + message.getMessage() + " 시간 : " + message.getTime());
            chatMessageRepository.save(message);

        }

        //메시지 브로커를 이용하여 클라이언트에게 메시지를 보냄
        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }

    @GetMapping("/chat/all/{roomId}")
    public List<ChatMessage> chatAll(ChatMessage message) {
        List<ChatMessage> chat = chatService.findAllRoomChat(message.getRoomId());
        System.out.println(chat);
        return chat;
    }

    @GetMapping("/chat/delete/{deleteId}")
    public void deleteCheat(@PathVariable  String deleteId) {
        chatService.deleteChat(deleteId);
    }

    @GetMapping("/chat/username")
    public String getName(@AuthenticationPrincipal MemberAdapter memberAdapter) {
        String username = memberAdapter.getMember().getNickname();
        return "{\"username\": \"" + username + "\"}";
    }

    @GetMapping("/chat/last")
    public Optional<ChatMessage> allRoomChat() {
        Optional<ChatMessage> chat = chatService.findLast();
        return chat;
    }

    @GetMapping("/chat/login/user")
    public String loginUser(@AuthenticationPrincipal MemberAdapter memberAdapter) {
        return memberAdapter.getMember().getNickname();
    }
}
