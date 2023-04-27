package com.example.randomchat.service;

import com.example.randomchat.entity.dto.MemberDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    void createAccount(MemberDto memberDto);

}
