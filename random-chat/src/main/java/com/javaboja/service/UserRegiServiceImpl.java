package com.javaboja.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaboja.Entity.FriendsList;
import com.javaboja.Entity.User;
import com.javaboja.repo.FriendsListRepo;
import com.javaboja.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserRegiServiceImpl implements UserRegiService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private FriendsListRepo friendsListRepo;
	@Override
	public Map<String, Object> regiService(User user) {
		Map<String, Object> reponseMap = new HashMap<>();
		try {
			boolean existsUserId = userRepo.existsById(user.getUserId());
			if(!existsUserId) {
				userRepo.save(user);
				reponseMap.put("code", 200);
			}else {
				reponseMap.put("code", 201);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			reponseMap.put("code", 400);
			reponseMap.put("error", e.toString());
		}
		return reponseMap;
	}

	@Override
	public Map<String, Object> loginService(User user) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			long userCount = userRepo.countByUserIdAndUserPassword(user.getUserId(), user.getUserPassword());
			if(userCount>0) {
				responseMap.put("code", 200);
				responseMap.put("friendsList",friendsListRepo.findByUserId(user.getUserId()));
			}else {
				responseMap.put("code", 202);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			responseMap.put("code", 400);
			responseMap.put("error", e.toString());
		}
		return responseMap;
	}

	@Override
	public void friendsAddService(FriendsList friendsList) {
		// TODO Auto-generated method stub
		
		FriendsList fl1 = new FriendsList();
		fl1.setChatId(friendsList.getChatId());
		fl1.setFriendsId(friendsList.getUserId());
		fl1.setUserId(friendsList.getFriendsId());
		friendsListRepo.save(fl1);
		
		FriendsList fl2 = new FriendsList();
		fl2.setChatId(friendsList.getChatId());
		fl2.setFriendsId(friendsList.getFriendsId());
		fl2.setUserId(friendsList.getUserId());
		friendsListRepo.save(fl2);
		
	}

}
