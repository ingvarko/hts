package com.hts.tests;

import java.net.UnknownHostException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hts.entities.IpAddress;
import com.hts.exceptions.AppException;
import com.hts.service.IpAddressServiceImpl;

public class TestIpAddressService {
	static IpAddressServiceImpl ipAddressService = new IpAddressServiceImpl();
	// static HotelServiceImpl hotelService = new HotelServiceImpl();

	final static String name = "111.111.111.111";

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
	}

	@Test
	public void testCreateIpAddressService() throws AppException, UnknownHostException {
		IpAddress ipAddress = ipAddressService.create(name);
		Assert.assertNotNull(ipAddress.getId());
	}

	@Test
	public void testInvalidIpAddressService() throws AppException {
		IpAddress ipAddress = null;
		try {
			ipAddress = ipAddressService.create("11s.111.111.111");
		} catch (UnknownHostException e) {
			Assert.assertNull(ipAddress);
		}
	}

	@Test
	public void testGetIpAddressByName() throws AppException, UnknownHostException {
		IpAddress ipAddress = ipAddressService.create(name);
		Assert.assertNotNull(ipAddress.getId());
		List<IpAddress> ipAddresss = ipAddressService.getByName(name);
		Assert.assertTrue(ipAddresss.size() >= 1);
	}

	public static void main(String[] args) throws Exception {
		// TestIpAddressService test = new TestIpAddressService();
		// test.testGetIpAddressByName();
		// NetworkServiceImpl.getAllIPsInLanNetwork();
	}

}
