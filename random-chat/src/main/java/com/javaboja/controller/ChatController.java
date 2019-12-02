package com.javaboja.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaboja.Entity.Chat;
import com.javaboja.Entity.Message;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ChatController {

	@MessageMapping("/chat/{chatId}")
	@SendTo("/topic/chat/{chatId}")
	public Message message(Message msg,
							@DestinationVariable("chatId") String chatId) throws Exception{
		
		log.info("chat : "+chatId);
		log.info(msg.getUserId()+ " : "+msg.getMessage());
		return msg;
	}
}
