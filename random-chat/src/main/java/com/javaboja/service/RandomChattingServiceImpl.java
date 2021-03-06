package com.javaboja.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.javaboja.Entity.Chat;
import com.javaboja.Entity.Message;
import com.javaboja.repo.ChatRepo;
import com.javaboja.repo.MessageRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RandomChattingServiceImpl implements RandomChattingService {
 
	@Autowired
	private ChatRepo chatRepo;
	@Autowired
	private MessageRepo messageRepo;
	@Autowired
	private SimpMessagingTemplate smt;
	@Override
	public Chat getChattingRoomIdService(String userId) {
		// TODO Auto-generated method stub
		Chat chat = new Chat();
		String chatId = UUID.randomUUID().toString();
		List<Chat> findByUserId2IsNull = chatRepo.findByUserId2IsNull();
		if(findByUserId2IsNull.size()>0){
			chat = findByUserId2IsNull.get(0);
			chat.setUserId2(userId);
			chatRepo.save(chat);
			smt.convertAndSend("/topic/hello/"+chat.getChatId(), chat);
			return chat;
		}else{
			chat.setChatId(chatId);
			chat.setUserId1(userId);
			chatRepo.save(chat);
			return chat;
		}

	}

	@Override
	public void messageSaveService(Message message) {
		// TODO Auto-generated method stub
		messageRepo.save(message);
	}

	@Override
	public List<Message> chattingReopenService(String chatId) {
		// TODO Auto-generated method stub
		return messageRepo.findByChatId(chatId);
	}

	@Override
	public void chattingOutService() {
		// TODO Auto-generated method stub
		
	}

	
}
