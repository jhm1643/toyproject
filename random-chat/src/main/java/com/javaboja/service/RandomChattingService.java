package com.javaboja.service;

import java.util.List;
import java.util.Map;

import com.javaboja.Entity.Chat;
import com.javaboja.Entity.Message;

public interface RandomChattingService {

	public Chat getChattingRoomIdService(String userId);
	public void messageSaveService(Message message);
	public List<Message> chattingReopenService(String chatId);
	public void chattingOutService();
}
