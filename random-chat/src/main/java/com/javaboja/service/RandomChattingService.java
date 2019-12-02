package com.javaboja.service;

import com.javaboja.Entity.Message;

public interface RandomChattingService {

	public String getChattingRoomIdService(String userId);
	public void messageSaveService(Message message);
}
