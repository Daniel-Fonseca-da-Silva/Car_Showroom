package com.auto.showroom.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Car {

	@Id // This is the PRIMARY KEY
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
	private Long id;
	private String name;
	private String description;
	private String urlPhoto;
	private String urlVideo;
	private String latitude;
	private String longitude;
	private String category;
}
