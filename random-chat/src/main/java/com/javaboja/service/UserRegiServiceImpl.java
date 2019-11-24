package com.javaboja.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaboja.Entity.User;
import com.javaboja.repo.UserRepo;

@Service
public class UserRegiServiceImpl implements UserRegiService {

	@Autowired
	private UserRepo userRepo;
	
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
		Map<String, Object> reponseMap = new HashMap<>();
		try {
			long userCount = userRepo.countByUserIdAndUserPassword(user.getUserId(), user.getUserPassword());
			if(userCount>0) {
				reponseMap.put("code", 200);
			}else {
				reponseMap.put("code", 202);
			}
		}catch(Exception e) {
			e.printStackTrace();
			reponseMap.put("code", 400);
			reponseMap.put("error", e.toString());
		}
		return reponseMap;
	}

}
