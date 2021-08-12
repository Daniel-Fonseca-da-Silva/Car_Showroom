package com.auto.showroom.domain;

import java.util.ArrayList;
import java.util.List;

public class CarService {

	public List<Car> getCar() {

		List<Car> cars = new ArrayList<>();

		cars.add(new Car(1L, "Graciela"));
		cars.add(new Car(2L, "Justicialista"));
		cars.add(new Car(3L, "Rastrojero"));
		cars.add(new Car(4L, "Zunder 1500"));
		cars.add(new Car(5L, "Ika Torino"));

		return cars;
	}

}
