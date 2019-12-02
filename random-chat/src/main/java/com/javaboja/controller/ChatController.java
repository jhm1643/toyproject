package com.javaboja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaboja.Entity.Chat;
import com.javaboja.Entity.Message;
import com.javaboja.service.RandomChattingService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ChatController {

	@Autowired
	private RandomChattingService rcs;
	
	@MessageMapping("/chat/{chatId}")
	@SendTo("/topic/chat/{chatId}")
	public Message message(Message message,
							@DestinationVariable("chatId") String chatId) throws Exception{
		message.setChatId(chatId);
		rcs.messageSaveService(message);
		return message;
	}
}
