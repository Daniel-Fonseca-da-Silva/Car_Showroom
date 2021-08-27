package com.auto.showroom.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.auto.showroom.domain.dto.CarDTO;

@Service
public class CarService {

	@Autowired
	private CarRepository repo;

	public List<CarDTO> getCars() {
		return repo.findAll().stream().map(CarDTO::create).collect(Collectors.toList());
	}
	
	public CarDTO getCarById(Long id) {
		Optional<Car> car = repo.findById(id);
		return car.map(CarDTO::create).orElseThrow(() -> new com.auto.showroom.api.exeception.ObjectNotFoundException("Don't possible to find this car")); 
	}

	public List<CarDTO> getCarByCategory(String category) {
		return repo.findByCategory(category).stream().map(CarDTO::create).collect(Collectors.toList());
	}

	public CarDTO insert(Car car) {
		Assert.isNull(car.getId(), "Don't possible insert this registry");
		return CarDTO.create(repo.save(car));
	}

	public CarDTO update(Car car, Long id) {

		Assert.notNull(id, "Don't possible updated this registry");

		// Search the car inside Data Base
		Optional<Car> optional = repo.findById(id);
		Car db = optional.get();

		// Pass the properties
		db.setName(car.getName());
		db.setCategory(car.getCategory());
		System.out.println("Car id: " + db.getId());

		// Save the car
		repo.save(db);
		return CarDTO.create(db);
	}

	public void delete(Long id) {
		repo.deleteById(id);

	}

}
