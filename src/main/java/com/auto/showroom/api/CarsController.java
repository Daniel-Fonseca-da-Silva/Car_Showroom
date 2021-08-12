package com.auto.showroom.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auto.showroom.domain.Car;
import com.auto.showroom.domain.CarService;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {

	private CarService service = new CarService();

	@GetMapping
	public List<Car> getCar() {

		return service.getCar();
	}

}
