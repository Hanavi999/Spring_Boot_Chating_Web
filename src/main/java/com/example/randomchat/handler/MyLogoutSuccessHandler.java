package com.example.randomchat.handler;

import com.example.randomchat.Repository.MemberRepository;
import com.example.randomchat.entity.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    private final MemberRepository memberRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if (authentication != null) {
            String username = authentication.getName();

            Member member = memberRepository.findByUsername(username).orElseThrow();
            memberRepository.save(member);
            response.setStatus(HttpServletResponse.SC_OK);
        }



        response.sendRedirect("/login");
    }
}
