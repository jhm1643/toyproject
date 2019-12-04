package com.javaboja.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaboja.Entity.FriendsList;

public interface FriendsListRepo extends JpaRepository<FriendsList, Long>{

	public List<FriendsList> findByUserId(String userId);
}
