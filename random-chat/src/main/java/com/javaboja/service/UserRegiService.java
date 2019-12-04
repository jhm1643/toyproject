package com.javaboja.service;

import java.util.Map;

import com.javaboja.Entity.FriendsList;
import com.javaboja.Entity.User;

public interface UserRegiService {

	public Map<String, Object> regiService(User user);
	public Map<String, Object> loginService(User user);
	public void friendsAddService(FriendsList friendsList);
}
