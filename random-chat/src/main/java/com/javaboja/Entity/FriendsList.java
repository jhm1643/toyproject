package com.javaboja.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="friendsList")
public class FriendsList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String chatId;
	private String userId;
	private String friendsId;
	private String message;
}
