package com.jason.stompwebsocket.scheduled;

import com.jason.stompwebsocket.model.Greeting;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageScheduled {

    private final SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedRate = 10)
    public void sendMessage() {
        messagingTemplate.convertAndSend("/topic/greetings", new Greeting("Hello I'm Jason"));
    }
}
