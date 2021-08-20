package com.auto.showroom.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<Iterable<Car>> getCar() {
		return ResponseEntity.ok(service.getCars());
//		return new ResponseEntity<>(service.getCars(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Car> getCarById(@PathVariable("id") Long id) {

		Optional<Car> car = service.getCarById(id);

//		return car.map(ResponseEntity::ok)
//		.orElse(ResponseEntity.notFound().build());

		return !car.isPresent() ? ResponseEntity.notFound().build() : ResponseEntity.ok(car.get());

//		if(!car.isPresent()) 
//			return ResponseEntity.notFound().build();
//		
//		return ResponseEntity.ok(car.get());

	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<Car>> getCarById(@PathVariable("category") String category) {

		List<Car> cars = service.getCarByCategory(category); 
		
		return cars.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cars);
	}

	@PostMapping
	public String post(@RequestBody Car car) {
		Car c = service.insert(car);

		return "Car saved: " + c.getId() + " with successfully";
	}

	@PutMapping("/{id}")
	public String put(@PathVariable("id") Long id, @RequestBody Car car) {
		Car c = service.update(car, id);

		return "Car updated: " + c.getId() + " with successfully";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);

		return "Car deleted with successfully";
	}

}
