package com.hts.tests;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hts.entities.Hotel;
import com.hts.exceptions.AppException;
import com.hts.service.HotelServiceImpl;

public class TestHotelService {
	static HotelServiceImpl hotelService = new HotelServiceImpl();
	final static String name = "testHotelService";

	/**
	 * @BeforeClass - oneTimeSetUp
	 * @Before - setUp
	 * @Test - testEmptyCollection
	 * @After - tearDown
	 * @AfterClass - oneTimeTearDown
	 */

	/**
	 * Setup and TearDown: You need not have to create setup and teardown
	 * methods for setup and teardown. The @Before, @After and @BeforeClass, @AfterClass
	 * annotations are used for implementing setup and teardown operations. The @Before
	 * and @BeforeClass methods are run before running the tests. The @After and @AfterClass
	 * methods are run after the tests are run. The only difference being that
	 * the @Before and @After can be used for multiple methods in a class, but
	 * the @BeforeClass and @AfterClass can be used only once per class.
	 */

	@BeforeClass
	public static void beforeClass() {
	}

	@Before
	public void setup() {
	}

	@After
	public void tearDown() {
	}

	@AfterClass
	public static void afterClass() throws AppException {
		List<Hotel> hotels = hotelService.getByName(name);
		for (Hotel hotel : hotels)
			hotelService.delete(hotel);
	}

	@Test
	public void testCreateHotelService() throws AppException {
		Hotel hotel = hotelService.create(name);
		Assert.assertNotNull(hotel.getUuId());
	}

	@Test
	public void testGetHotelByName() throws AppException {
		Hotel hotel = hotelService.create(name);
		Assert.assertNotNull(hotel.getUuId());
		List<Hotel> hotels = hotelService.getByName(name);
		Assert.assertTrue(hotels.size() >= 1);
	}

	public static void main(String[] args) throws AppException {
		TestHotelService test = new TestHotelService();
		test.testGetHotelByName();
	}

}
