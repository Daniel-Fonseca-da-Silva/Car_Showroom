package com.auto.showroom.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auto.showroom.domain.Car;
import com.auto.showroom.domain.CarService;
import com.auto.showroom.domain.dto.CarDTO;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {

	@Autowired
	private CarService service;

	@GetMapping
	public ResponseEntity<List<CarDTO>> getCar() {
		return ResponseEntity.ok(service.getCars());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarDTO> getCarById(@PathVariable("id") Long id) {
		CarDTO car = service.getCarById(id);
		return ResponseEntity.ok(car);
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<CarDTO>> getCarById(@PathVariable("category") String category) {
		List<CarDTO> cars = service.getCarByCategory(category);
		return cars.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cars);
	}

	@PostMapping
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<List<CarDTO>> post(@RequestBody Car car) {
		CarDTO c = service.insert(car);
		URI location = getUri(c.getId());
		return ResponseEntity.created(location).build();
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@PutMapping("/{id}")
	public ResponseEntity<CarDTO> put(@PathVariable("id") Long id, @RequestBody Car car) {
		try {
			CarDTO c = service.update(car, id);
			return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CarDTO> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
