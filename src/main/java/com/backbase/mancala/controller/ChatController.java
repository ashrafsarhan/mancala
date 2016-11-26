package com.backbase.mancala.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author ashraf
 *
 */
@Controller
public class ChatController {


    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Message(message.getContent());
    }

}