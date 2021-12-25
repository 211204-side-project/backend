package com.scope.socialboardweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * HTTP 핸드쉐이킹을 위한 주소 설정
     * (소켓 접속)
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp").withSockJS();
    }

    /**
     * 메시지 브로커 관련 설정
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // destination이 '/topic'으로 시작하면, SimpleBroker를 사용한다.
        registry.enableSimpleBroker("/topic");

        // destination이 '/app'으로 시작하면, 메시지 가공을 위한 핸들러로 라우팅된다.
        registry.setApplicationDestinationPrefixes("/app");
    }
}
