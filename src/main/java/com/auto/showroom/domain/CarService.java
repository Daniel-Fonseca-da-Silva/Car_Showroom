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

	public Optional<CarDTO> getCarById(Long id) {
		return repo.findById(id).map(CarDTO::create);

//		Optional<Car> car = repo.findById(id);
//		if(!car.isPresent()) {
//			return null;
//		}
//		return Optional.of(new CarDTO(car.get()));
	}

	public List<CarDTO> getCarByCategory(String category) {
		return repo.findByCategory(category).stream().map(CarDTO::create).collect(Collectors.toList());
	}

	public Car insert(Car car) {
		Assert.isNull(car.getId(), "Don't possible insert this registry");
		return repo.save(car);
	}

	public Car update(Car car, Long id) {
		return repo.save(car);
	}

	public void delete(Long id) {

		if (!getCarById(id).isPresent())
			throw new RuntimeException("Don't possible delete this registry");

		repo.deleteById(id);
	}

}
