package com.example;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Push {

	private String message;
	public Push() {}
	public Push(String message) {
		this.message=message;
	}
}
