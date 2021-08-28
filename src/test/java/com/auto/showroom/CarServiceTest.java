package com.auto.showroom;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.auto.showroom.api.exeception.ObjectNotFoundException;
import com.auto.showroom.domain.Car;
import com.auto.showroom.domain.CarService;
import com.auto.showroom.domain.dto.CarDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
class CarServiceTest {

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
		c = service.getCarById(id);
		assertNotNull(c);

		assertEquals("Graciela", c.getName());
		assertEquals("classic", c.getCategory());

		// Remove the object
		service.delete(id);

		// Verify if the object is deleted
		try {
			assertNull(service.getCarById(id));
			fail("Don't possible remove this car");
		} catch(ObjectNotFoundException e ) {
			// OK
		}
	}

	@Test
	void testList() {
		List<CarDTO> cars = service.getCars();
		assertEquals(30, cars.size());
	}

	@Test
	void testGet() {
		CarDTO c = service.getCarById(11L);
		assertNotNull(c);
		assertEquals("Ferrari FF", c.getName());
	}

	@Test
	void testListCategory() {
		assertEquals(10, service.getCarByCategory("classic").size());
		assertEquals(10, service.getCarByCategory("sport").size());
		assertEquals(10, service.getCarByCategory("luxury").size());
		assertEquals(0, service.getCarByCategory("esporte").size());
	}

}
