package com.auto.showroom.domain;

import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long>{

	Iterable<Car> findByCategory(String category);
	
	
}
