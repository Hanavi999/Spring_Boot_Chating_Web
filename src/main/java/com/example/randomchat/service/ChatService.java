package com.example.randomchat.service;

import com.example.randomchat.Repository.ChatMessageRepository;
import com.example.randomchat.Repository.ChatRoomRepository;
import com.example.randomchat.Repository.MemberRepository;
import com.example.randomchat.entity.ChatMessage;
import com.example.randomchat.entity.ChatRoom;
import com.example.randomchat.entity.Member;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final MemberRepository memberRepository;

    private Map<String, ChatRoom> chatRooms;

    private final ChatRoomRepository chatRoomRepository;

    private final ChatMessageRepository chatMessageRepository;

    @PostConstruct
    //의존관게 주입완료되면 실행되는 코드
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    //채팅방 불러오기
    public List<ChatRoom> findAllRoom() {
        List<ChatRoom> result = new ArrayList<>(chatRoomRepository.findAll());
        Collections.reverse(result);

        return result;
    }

    //채팅방 하나 불러오기
    public ChatRoom findById(String roomId) {
        return chatRoomRepository.findByRoomId(roomId);
    }

    //채팅방 생성
    public ChatRoom createRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        chatRoomRepository.save(chatRooms.put(chatRoom.getRoomId(), chatRoom));
        return chatRoom;
    }

    public List<ChatMessage> findAllRoomChat(String roomId) {
        return chatMessageRepository.findByRoomId(roomId);
    }

    public ChatRoom findByChatName(String roomId) {
        return chatRoomRepository.findByRoomId(roomId);
    }

}
