package com.javaboja.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user")
public class User {

	@Id
	private String userId;
	private String userPassword;
	private String loginYN;
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//	private List<Message> messages;
	
	
}
