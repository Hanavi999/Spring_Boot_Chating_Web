package com.example.randomchat.entity.dto;

import lombok.Data;

@Data
public class MemberDto {

    private String username;

    private String password;

    private String nickname;

    private int online;
}

