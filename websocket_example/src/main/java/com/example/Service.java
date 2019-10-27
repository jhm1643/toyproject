package com.example;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@org.springframework.stereotype.Service
public class Service {

	@Autowired
	private SimpMessagingTemplate smt;
	
	public void test() {
		smt.convertAndSend("/topic/chat", new Chat("test","test"));
	}
}
