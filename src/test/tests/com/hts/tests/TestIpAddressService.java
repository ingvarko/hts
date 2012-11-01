package com.hts.tests;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hts.entities.Channel;
import com.hts.entities.IpAddress;
import com.hts.entities.Room;
import com.hts.entities.SubscriptionPackage;
import com.hts.exceptions.AppException;
import com.hts.service.ChannelServiceImpl;
import com.hts.service.IpAddressServiceImpl;
import com.hts.service.RoomServiceImpl;
import com.hts.service.SubscriptionPackageServiceImpl;

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
	 * Setup and TearDown: You need not have to create setup and teardown methods for setup and teardown. The @Before, @After
	 * and @BeforeClass, @AfterClass annotations are used for implementing setup and teardown operations. The @Before
	 * and @BeforeClass methods are run before running the tests. The @After and @AfterClass methods are run after the
	 * tests are run. The only difference being that the @Before and @After can be used for multiple methods in a class,
	 * but the @BeforeClass and @AfterClass can be used only once per class.
	 */

	@BeforeClass
	public static void beforeClass() {
	}

	@Before
	public void setup() throws AppException {
		String name = "222.222.222.222";
		IpAddress ipAddress = ipAddressService.getByIp(name);

		ipAddressService.delete(ipAddress);

		name = "111.111.111.111";
		ipAddress = ipAddressService.getByIp(name);

		ipAddressService.delete(ipAddress);
	}

	@After
	public void tearDown() {
	}

	@AfterClass
	public static void afterClass() throws AppException {
		IpAddress ipAddress = ipAddressService.getByIp(name);
		ipAddressService.delete(ipAddress);
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
		}
		catch (UnknownHostException e) {
			Assert.assertNull(ipAddress);
		}
	}

	@Test
	public void testGetIpAddressByName() throws AppException, UnknownHostException {
		IpAddress ipAddress = ipAddressService.create(name);
		Assert.assertNotNull(ipAddress.getId());
		ipAddress = ipAddressService.getByIp(name);
		Assert.assertNotNull(ipAddress.getId());
	}

	@Test
	public void testIsBroadcastStreamAllowedForIP() throws AppException, UnknownHostException {

		// Begin of clean up
		// --------------------------------------------------------
		RoomServiceImpl roomServiceImpl = new RoomServiceImpl();
		ChannelServiceImpl channelServiceImpl = new ChannelServiceImpl();
		SubscriptionPackageServiceImpl subscriptionPackageServiceImpl = new SubscriptionPackageServiceImpl();

		IpAddress ipAddress = ipAddressService.getByIp("222.222.222.222");
			ipAddressService.delete(ipAddress);

		List<Room> rooms = roomServiceImpl.getByName("222.222.222.222");
		for (Room r : rooms)
			roomServiceImpl.delete(r);

		List<Channel> ss = channelServiceImpl.getByName("222.222.222.222");
		for (Channel s : ss)
			channelServiceImpl.delete(s);

		List<SubscriptionPackage> subscriptionPackages = subscriptionPackageServiceImpl.getByName("222.222.222.222");
		for (SubscriptionPackage subscriptionPackage : subscriptionPackages) {
			subscriptionPackage.setChannels(null);
			subscriptionPackageServiceImpl.update(subscriptionPackage);
			subscriptionPackageServiceImpl.delete(subscriptionPackage);
		}

		// End of clean up
		// ----------------------------------------------------------------------------

		Room room = roomServiceImpl.create("222.222.222.222");

		ipAddress = ipAddressService.create("222.222.222.222");
		ipAddress.setRoom(room);
		ipAddressService.update(ipAddress);

		Channel channel = channelServiceImpl.create("222.222.222.222", "222.222.222.222");

		SubscriptionPackage subscriptionPackage = subscriptionPackageServiceImpl.create("222.222.222.222");

		ArrayList<Channel> channels = new ArrayList<Channel>();
		channels.add(channel);
		subscriptionPackage.setChannels(channels);

		subscriptionPackageServiceImpl.update(subscriptionPackage);

		room.setSubscriptionPackage(subscriptionPackage);
		roomServiceImpl.update(room);

		if (ipAddressService.isBroadcastStreamAllowedForIP("222.222.222.222", "222.222.222.222"))
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
	
	}

	@Test
	public void testManyToOne() throws AppException, UnknownHostException {
		IpAddress ipAddress = ipAddressService.create(name);

		String roomName = "TestManyToMany";
		RoomServiceImpl roomServiceImpl = new RoomServiceImpl();

		ipAddress.setRoom(roomServiceImpl.create(roomName));
		ipAddressService.update(ipAddress);

		Assert.assertNotNull(ipAddress.getId());

		ipAddressService.delete(ipAddress);
		while (!roomServiceImpl.getByName(roomName).isEmpty())
			roomServiceImpl.delete(roomServiceImpl.getByName(roomName).get(0));

	}

}
