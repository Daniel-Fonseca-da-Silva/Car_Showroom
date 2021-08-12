package com.auto.showroom.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
	
	@Autowired
	private CarRepository repo;
	
	public Iterable<Car> getCars() {
		return repo.findAll();
	}
	
	public Optional<Car> getCarById(Long id) {
		return repo.findById(id);
	}

	public List<Car> getCarsFake() {

		List<Car> cars = new ArrayList<>();

		cars.add(new Car(1L, "Graciela"));
		cars.add(new Car(2L, "Justicialista"));
		cars.add(new Car(3L, "Rastrojero"));
		cars.add(new Car(4L, "Zunder 1500"));
		cars.add(new Car(5L, "Ika Torino"));

		return cars;
	}

	public Iterable<Car> getCarByCategory(String category) {
		
		return repo.findByCategory(category);
	}

}
