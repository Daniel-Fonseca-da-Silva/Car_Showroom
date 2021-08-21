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

		return repo.findAll().stream().map(CarDTO::new).collect(Collectors.toList());

//		List<CarDTO> list = new ArrayList<>();
//		for(Car c : cars) {
//			list.add(new CarDTO(c));
//		}
//		return list;
	}

	public Optional<Car> getCarById(Long id) {
		return repo.findById(id);
	}

	public List<CarDTO> getCarByCategory(String category) {

		return repo.findByCategory(category).stream().map(CarDTO::new).collect(Collectors.toList());
	}

	public Car insert(Car car) {
		Assert.isNull(car.getId(), "Don't possible insert this registry");
		return repo.save(car);
	}

	public Car update(Car car, Long id) {

		Assert.notNull(id, "Don't possible updated this registry");

		// Search the car inside Data Base
		Optional<Car> optional = getCarById(id);

		if (!optional.isPresent())
			throw new RuntimeException("Don't possible updated this registry");

		Car db = optional.get();
		// Pass the properties
		db.setName(car.getName());
		db.setCategory(car.getCategory());
		System.out.println("Car id: " + db.getId());

		// Save the car
		repo.save(db);
		return db;
	}

	public void delete(Long id) {

		Optional<Car> car = getCarById(id);

		if (!car.isPresent())
			throw new RuntimeException("Don't possible delete this registry");

		repo.deleteById(id);
	}

}
