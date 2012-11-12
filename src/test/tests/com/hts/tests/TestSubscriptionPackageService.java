package com.hts.tests;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hts.entity.Channel;
import com.hts.entity.SubscriptionPackage;
import com.hts.exceptions.AppException;
import com.hts.service.ChannelServiceImpl;
import com.hts.service.SubscriptionPackageServiceImpl;

public class TestSubscriptionPackageService {
	static SubscriptionPackageServiceImpl subscriptionPackageService = new SubscriptionPackageServiceImpl();
	static ChannelServiceImpl channelService = new ChannelServiceImpl();
	
	final static String name = "testSubscriptionPackageService";
	
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
		List<SubscriptionPackage> subscriptionPackages = subscriptionPackageService.getByName(name);
		for (SubscriptionPackage subPackage : subscriptionPackages){
			subPackage .setChannels(null);
			subscriptionPackageService.delete(subPackage);
		}

		for (Channel ch :channelService.getByName("testAddChanneltoSubscription")){
			channelService.delete(ch);
		}
	}

	@Before
	public void setup() {
	}

	@After
	public void tearDown() {
	}

	@AfterClass
	public static void afterClass() throws AppException {
		
		List<SubscriptionPackage> subscriptionPackages = subscriptionPackageService.getByName(name);
		for (SubscriptionPackage subPackage : subscriptionPackages){
			subPackage .setChannels(null);
			subscriptionPackageService.delete(subPackage);
		}

		for (Channel ch :channelService.getByName("testAddChanneltoSubscription")){
			channelService.delete(ch);
		}
	}

	@Test
	public void testCreateSubscriptionService() throws AppException {
		SubscriptionPackage subscriptionPackage = subscriptionPackageService.create(name);
		Assert.assertNotNull(subscriptionPackage.getId());
	}

	
	@Test
	public void testGetSubscriptionByName() throws AppException {
		SubscriptionPackage subscriptionPackage = subscriptionPackageService.create(name);
		Assert.assertNotNull(subscriptionPackage.getId());

		List<SubscriptionPackage> subscriptionPackages = subscriptionPackageService.getByName(name);
		Assert.assertTrue(subscriptionPackages.size() >= 1);
	}

	@Test
	public void testAddChanneltoSubscription() throws AppException {

		List<SubscriptionPackage> subscriptionPackages = subscriptionPackageService.getByName(name);
		for (SubscriptionPackage subPackage : subscriptionPackages){
			subPackage .setChannels(null);
			subscriptionPackageService.delete(subPackage);
		}

		for (Channel ch :channelService.getByName("testAddChanneltoSubscription")){
			channelService.delete(ch);
		}
		
		SubscriptionPackage subscriptionPackage = subscriptionPackageService.create(name);
		Channel chan= channelService.create("testAddChanneltoSubscription", "testAddChanneltoSubscription");
		subscriptionPackageService.addChannel(subscriptionPackage, chan);
		Assert.assertNotNull(subscriptionPackage.getId());

	}

	public static void main(String[] args) throws Exception {
		new TestSubscriptionPackageService().testAddChanneltoSubscription();
		}

}
