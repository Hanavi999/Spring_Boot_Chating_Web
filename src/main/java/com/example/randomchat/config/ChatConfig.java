package com.example.randomchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class ChatConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // setAllowedOriginPatterns(*) = 모든 도메인에서 접근 허용
        // withSockJS() = WebSocket 을 지원하지 않는 브라우저에서도 통신이 가능하게 함
        registry.addEndpoint("/ws/chat").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override // 웹소켓 메시지 브로커 구성
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메시지 받을 때 경로 설정 /queue는 1대1 /topic은 1대다 메시징일 때 주로 사용
        registry.enableSimpleBroker("/queue", "/topic");
        // 클라이언트가 메시지 보낼 때 경로앞에 /app 붙어있으면 Broker 로 보내짐.
        registry.setApplicationDestinationPrefixes("/app");
    }

}
