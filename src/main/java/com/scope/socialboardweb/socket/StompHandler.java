package com.scope.socialboardweb.socket;

import com.scope.socialboardweb.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class StompHandler {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/{commentedPostId}")
    public void alertComment(@Payload CommentRequestDto commentRequestDto, @DestinationVariable Long commentedPostId) {
        log.info("STOMP 핸들러 동작");
        String destination = "/topic/" + commentedPostId;
        simpMessagingTemplate.convertAndSend(destination, commentRequestDto);
    }
}
