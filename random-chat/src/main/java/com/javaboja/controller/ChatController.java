package com.javaboja.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

//	@MessageMapping("/chat")
//	@SendTo("/topic/chat")
//	public Chat chat(Chat chat) throws Exception{
//		log.info("chat");
//		return new Chat(chat.getName(), chat.getMessage());
//	}
}
