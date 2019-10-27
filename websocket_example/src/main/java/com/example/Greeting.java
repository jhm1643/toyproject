package com.example;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class Greeting {

	private String content;
	public Greeting() {}
	public Greeting(String content) {
		this.content=content;
	}
}
