package com.javaboja.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.javaboja.Entity.Chat;
import com.javaboja.repo.ChatRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RandomChattingServiceImpl implements RandomChattingService {
 
	@Autowired
	private ChatRepo chatRepo;

	@Override
	public String getChattingRoomIdService(String userId) {
		// TODO Auto-generated method stub
		Chat chat = new Chat();
		String chatId = UUID.randomUUID().toString();
		List<Chat> findByUserId2IsNull = chatRepo.findByUserId2IsNull();
		if(findByUserId2IsNull.size()>0){
			chat = findByUserId2IsNull.get(0);
			chat.setUserId2(userId);
			chatRepo.save(chat);
			return chat.getChatId();
		}else{
			chat.setChatId(chatId);
			chat.setUserId1(userId);
			chatRepo.save(chat);
			return chat.getChatId();
		}
//		while(true) {
//			if(WATTING_USER_QUEUE.size()>1) {
//				Chat chat = new Chat();
//				chat.setChatId(UUID.randomUUID().toString());
//				DeferredResult<Chat> result = new DeferredResult<>();
//				result.setResult(chat);
//				return result;
//			}
//			try {
//				Thread.sleep(5000);
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}

	
}
