package com.example.randomchat.entity.dto;

import com.example.randomchat.entity.ChatMessage;
import lombok.Data;

@Data
public class ChatMessageDto {

    public enum MessageType {
        ENTER, TALK
    }

    private ChatMessage.MessageType type;
    //채팅방 ID
    private String roomId;
    //보내는 사람
    private String sender;
    //내용
    private String message;

    private String time;

}
