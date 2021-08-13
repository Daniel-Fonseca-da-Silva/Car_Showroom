package com.auto.showroom.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/category/{category}")
	public Iterable<Car> getCarById(@PathVariable("category") String category) {
		
		return service.getCarByCategory(category);
	}
	
	@PostMapping
	public String post(@RequestBody Car car) {
		Car c = service.insert(car);
		
		return "Car saved: " + c.getId() + " with successfully";
	}

}
