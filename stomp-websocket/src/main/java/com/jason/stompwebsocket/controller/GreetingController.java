package com.jason.stompwebsocket.controller;

import com.jason.stompwebsocket.model.Greeting;
import com.jason.stompwebsocket.model.HelloMessage;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.Map;

@Controller
public class GreetingController {

    // MessageMapping 用於接收前端的訊息
    // SendTo 用於發送訊息給前端
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message, @Headers Map<String, Object> headers) throws Exception {
        System.out.println("接收到訊息" + message);

//        String simpDestination = (String) headers.get("simpDestination");

        return new Greeting("[APP] Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
