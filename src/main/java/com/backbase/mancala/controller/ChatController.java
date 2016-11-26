package com.backbase.mancala.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.backbase.mancala.dto.Message;

/**
 * The Class ChatController.
 *
 * @author ashraf
 */
@Controller
public class ChatController {


    /**
     * Send message.
     *
     * @param message the message
     * @return the message
     * @throws Exception the exception
     */
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Message(message.getContent());
    }

}