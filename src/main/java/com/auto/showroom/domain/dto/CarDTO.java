package com.auto.showroom.domain.dto;

import com.auto.showroom.domain.Car;

import lombok.Data;

@Data
public class CarDTO {

	private Long id = 0L;
	private String name = "";
	private String category = "";
	
	public CarDTO(Car c) {
		this.id = c.getId();
		this.name = c.getName();
		this.category = c.getCategory();
	}
	
}
