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
@Table(name="chat")
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long chatId;
}
