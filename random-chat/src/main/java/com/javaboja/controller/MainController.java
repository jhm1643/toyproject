package com.javaboja.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.javaboja.Entity.Chat;
import com.javaboja.Entity.FriendsList;
import com.javaboja.Entity.Message;
import com.javaboja.Entity.User;
import com.javaboja.service.RandomChattingService;
import com.javaboja.service.UserRegiService;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MainController {

	@Autowired
	private UserRegiService userRegiService;
	@Autowired
	private RandomChattingService rcs;
	
	@PostMapping("/regi")
	public Map<String, Object> regi(@RequestParam String userId,
									@RequestParam String userPassword) {
		User user = new User();
		user.setUserId(userId);
		user.setUserPassword(userPassword);
		Map<String, Object> map = userRegiService.regiService(user);
		return map;
	}
	@PostMapping("/main")
	public Map<String, Object> main(@RequestParam String userId,
									@RequestParam String userPassword) {
		User user = new User();
		user.setUserId(userId);
		user.setUserPassword(userPassword);
		Map<String, Object> map = userRegiService.loginService(user);
		return map;
	}
	
	@GetMapping("/ran-chat-start")
	public Chat ran_chat_start(@RequestParam(name = "userId") String userId) {
		
		return rcs.getChattingRoomIdService(userId);
	}
	
	@MessageMapping("/chat/{chatId}")
	@SendTo("/topic/chat/{chatId}")
	public Message message(Message message,
							@DestinationVariable("chatId") String chatId) throws Exception{
		
		message.setChatId(chatId);
		rcs.messageSaveService(message);
		return message;
	}
	
	@MessageMapping("/friend-add/{chatId}")
	@SendTo("/topic/friend-add/{chatId}")
	public FriendsList friendAdd(FriendsList friendsList,
							@DestinationVariable("chatId") String chatId) throws Exception{
		
		if(Optional.ofNullable(friendsList.getMessage()).orElse("").equals("ok")){
			friendsList.setChatId(chatId);
			userRegiService.friendsAddService(friendsList);
		}
		return friendsList;
	}
	
//	@GetMapping("/chat-out/{chatId}")
//	public String chatOut(@PathVariable(name ="chatId") String chatId) {
//		
//	}
//	@PostMapping("/friend-add")
//	public String friendAdd(@RequestBody FriendsList FriendsList){
//		userRegiService.friendsAddService(friendsList);
//		return "success";
//	}
	///friends-chat-reopen/"+chatId
	@GetMapping("friends-chat-reopen/{chatId}")
	public List<Message> chatReopen(@PathVariable(name = "chatId") String chatId){
		return rcs.chattingReopenService(chatId);
	}
}
