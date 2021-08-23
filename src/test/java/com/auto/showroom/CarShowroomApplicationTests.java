package com.auto.showroom;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.auto.showroom.domain.Car;
import com.auto.showroom.domain.CarService;
import com.auto.showroom.domain.dto.CarDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
class CarShowroomApplicationTests {

	@Autowired
	private CarService service;

	@Test
	void testInsert() {

		Car car = new Car();
		car.setName("Graciela");
		car.setCategory("classic");
		CarDTO c = service.insert(car);

		assertNotNull(c);
		Long id = c.getId();
		assertNotNull(id);

		// Search for the object
		Optional<CarDTO> op = service.getCarById(id);
		assertTrue(op.isPresent());

		c = op.get();
		assertEquals("Graciela", c.getName());
		assertEquals("classic", c.getCategory());

		// Remove the object
		service.delete(id);

		// Verify if the object is deleted
		assertFalse(service.getCarById(id).isPresent());
	}

	@Test
	void testList() {
		
	}

}
