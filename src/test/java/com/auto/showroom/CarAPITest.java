package com.auto.showroom;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.auto.showroom.domain.Car;
import com.auto.showroom.domain.CarService;
import com.auto.showroom.domain.dto.CarDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarShowroomApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarAPITest {
	
	@Autowired
	protected TestRestTemplate rest;

	@SuppressWarnings("unused")
	@Autowired
	private CarService service;

	private ResponseEntity<CarDTO> getCar(String url) {
		return rest.getForEntity(url, CarDTO.class);
	}

	private ResponseEntity<List<CarDTO>> getCars(String url) {
		return rest.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CarDTO>>() {
		});
	}

	@Test
	public void testSave() {

		Car car = new Car();
		car.setName("Porshe");
		car.setCategory("esport");

		// Insert
		ResponseEntity<CarDTO> response = rest.postForEntity("/api/v1/cars", car, null);
		System.out.println(response);
		
		// Verify if was create
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		// Search the object
		String location = response.getHeaders().get("location").get(0);
		CarDTO c = getCar(location).getBody();

		assertNotNull(c);
		assertEquals("Porshe", c.getName());
		assertEquals("esport", c.getCategory());

		// Remove the object
		rest.delete(location);

		// Verify if was deleted
		assertEquals(HttpStatus.NOT_FOUND, getCar(location).getStatusCode());
	}

	@Test
	public void testList() {
		List<CarDTO> cars = getCars("/api/v1/cars").getBody();
		assertNotNull(cars);
		assertEquals(30, cars.size());
	}

	@Test
	public void testListForCategory() {

		assertEquals(10, getCars("/api/v1/cars/category/classic").getBody().size());
		assertEquals(10, getCars("/api/v1/cars/category/esport").getBody().size());
		assertEquals(10, getCars("/api/v1/cars/category/luxury").getBody().size());

		assertEquals(HttpStatus.NO_CONTENT, getCars("/api/v1/cars/category/classico").getStatusCode());
	}

	@Test
	public void testGetOk() {

		ResponseEntity<CarDTO> response = getCar("/api/v1/cars/11");
		assertEquals(response.getStatusCode(), HttpStatus.OK);

		CarDTO c = response.getBody();
		assertEquals("Ferrari FF", c.getName());
	}

	@Test
	public void testGetNotFound() {

		ResponseEntity<CarDTO> response = getCar("/api/v1/cars/1100");
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
	}
}