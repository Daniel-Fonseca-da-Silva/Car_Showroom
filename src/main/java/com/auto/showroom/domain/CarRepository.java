package com.auto.showroom.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long>{

	List<Car> findByCategory(String category);
	
	
}
