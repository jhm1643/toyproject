package com.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chat {

	private String name;
	private String message;
	public Chat() {}
	public Chat(String name, String message) {
		this.name=name;
		this.message=message;
	}
}
