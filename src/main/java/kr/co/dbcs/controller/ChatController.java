package kr.co.dbcs.controller;

import kr.co.dbcs.model.ChatMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.HashMap;

@Log4j2
@EnableWebSocket
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/chat/message")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public void sendMsg(@Payload HashMap<String, String> map){
        log.info(map);
        simpMessagingTemplate.convertAndSend("/sub/chat/room/1", map);
    }
}