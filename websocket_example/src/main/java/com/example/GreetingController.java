package com.example;

import javax.servlet.http.PushBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class GreetingController {

	@Autowired
	private Service service;
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception{
		return new Greeting("Helo, "+HtmlUtils.htmlEscape(message.getName()+"!"));
	}
	
	@MessageMapping("/chat")
	@SendTo("/topic/chat")
	public Chat chat(Chat chat) throws Exception{
		log.info("chat");
		return new Chat(chat.getName(), chat.getMessage());
	}
	
	@RequestMapping("/test/{message}")
	@ResponseBody
	public String test(@PathVariable(name = "message") String message) throws Exception{
		service.test(message);
		return "push";
	}
	
	@RequestMapping("/test2")
	@ResponseBody
	@SendTo("/topic/push")
	public void test2() throws Exception{
		log.info("carrey");
	}
}
