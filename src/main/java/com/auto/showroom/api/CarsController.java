package com.auto.showroom.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auto.showroom.domain.Car;
import com.auto.showroom.domain.CarService;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {
	
	@Autowired
	private CarService service;

	@GetMapping
	public Iterable<Car> getCar() {

		return service.getCars();
	}
	
	@GetMapping("/{id}")
	public Optional<Car> getCarById(@PathVariable("id") Long id) {

		return service.getCarById(id);
	}

}
