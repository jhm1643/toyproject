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
@Table(name="message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long messageId;
	
	private String chatId;
	private String userId;
	private String message;
	//private Timestamp date;
}
