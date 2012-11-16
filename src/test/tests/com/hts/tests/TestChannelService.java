package com.hts.tests;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hts.entity.Channel;
import com.hts.exceptions.AppException;
import com.hts.service.ChannelServiceImpl;

public class TestChannelService {
	static ChannelServiceImpl channelService = new ChannelServiceImpl();

	final static String name = "testChanelService";
	final static String broadcastStreamName = "testBroadCastStreamName";
	
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
	 * @throws AppException 
	 */

	@BeforeClass
	public static void beforeClass() throws AppException {
		List<Channel> channels = channelService.getByName(name);
		for (Channel channel : channels)
			channelService.delete(channel);
	}

	@Before
	public void setup() {
	}

	@After
	public void tearDown() {
	}

	@AfterClass
	public static void afterClass() throws AppException {
		List<Channel> channels = channelService.getByName(name);
		for (Channel channel : channels)
			channelService.delete(channel);
	}

	@Test
	public void testCreateChannelService() throws AppException {
		List<Channel> channels = channelService.getByName(name);
		for (Channel channel : channels)
			channelService.delete(channel);
		
		Channel channel = channelService.create(name,broadcastStreamName);
		Assert.assertNotNull(channel.getId());
	}

	@Test
	public void testGetChannelByName() throws AppException {
		List<Channel> channels = channelService.getByName(name);
		for (Channel channel : channels)
			channelService.delete(channel);

		Channel channel = channelService.create(name,broadcastStreamName);
		Assert.assertNotNull(channel.getId());
		channels = channelService.getByName(name);
		Assert.assertTrue(channels.size() >= 1);
	}

	public static void main(String[] args) throws Exception {
//		TestChannelService test = new TestChannelService();
//		test.testGetChannelByName();
//		NetworkServiceImpl.getAllIPsInLanNetwork();
	}

}
