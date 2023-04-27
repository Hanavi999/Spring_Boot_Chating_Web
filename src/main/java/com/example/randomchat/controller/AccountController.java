package com.example.randomchat.controller;

import com.example.randomchat.entity.dto.MemberDto;
import com.example.randomchat.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "/chat/login";
    }

    @GetMapping("/register")
    public String register() {
        return "/chat/register";
    }

    @PostMapping("/registerOk")
    public String registerOk(MemberDto memberDto) {
        memberService.createAccount(memberDto);
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
    }

}
