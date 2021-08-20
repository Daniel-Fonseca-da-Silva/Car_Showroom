package com.auto.showroom.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long>{

	List<Car> findByCategory(String category);
	
	
}
