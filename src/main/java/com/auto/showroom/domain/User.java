package com.auto.showroom.domain;

import javax.persistence.Entity;

@Entity
public class User {
	
	private Long id;
	private String name;
	private String login;
	private String password;
	private String email;
}
