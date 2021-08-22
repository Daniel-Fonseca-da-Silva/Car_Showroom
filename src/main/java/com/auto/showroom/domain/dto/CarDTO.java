package com.auto.showroom.domain.dto;

import org.modelmapper.ModelMapper;

import com.auto.showroom.domain.Car;

import lombok.Data;

@Data
public class CarDTO {

	private Long id = 0L;
	private String name = "";
	private String category = "";
	
	// Convert attributes if is the same of DTO
	public static CarDTO create(Car c) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(c, CarDTO.class);
	}
	
}
