package com.auto.showroom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.auto.showroom.domain.Car;
import com.auto.showroom.domain.CarService;

@SpringBootTest
class CarShowroomApplicationTests {

	@Autowired
	private CarService service;

	@Test
	public void insert() {

		Car car = new Car();
		car.setName("Graciela");
		car.setCategory("classic");
		service.insert(car);
	}

	@Test
	public void test2() {

	}

}
