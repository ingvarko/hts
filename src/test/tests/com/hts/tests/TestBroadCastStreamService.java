package com.hts.tests;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hts.entity.BroadcastStream;
import com.hts.exceptions.AppException;
import com.hts.service.BroadcastStreamServiceImpl;


public class TestBroadCastStreamService {
	static BroadcastStreamServiceImpl broadcastStreamService = new BroadcastStreamServiceImpl();
	static String name = "testCreateBroadcastStreamService";


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
		List<BroadcastStream> broadcastStreams =  broadcastStreamService.getByName(name);
		for (BroadcastStream broadcastStream : broadcastStreams)
			broadcastStreamService.delete(broadcastStream);
	}

	// getAllBroadcastStreams()
	// getBroadcastStream(Integer streamId)

	@Test
	public void testRegisterBroadcastStreamService() throws AppException {
		BroadcastStream stream = broadcastStreamService
				.create(name);
		Assert.assertNotNull(stream.getId());
	}

	@Test
	public void testUnregisterBroadcastStreamService() throws AppException {
		BroadcastStream stream = broadcastStreamService
				.create(name);
		Assert.assertTrue(stream.isActive());

//		stream = broadcastStreamService
//		.unregisterBroadcastStream(stream.getName());
		broadcastStreamService
		.unregisterBroadcastStream(stream.getName());
		
//		Assert.assertFalse(stream.isActive());
//		TODO: check result of those broadcaststreams.
	}
	
	@Test
	public void testGetAllBroadcastStreams() throws AppException {
//		BroadcastStream stream = broadcastStreamService
//				.registerBroadcastStream(name);
//		List<BroadcastStream> streams= broadcastStreamService.getAllBroadcastStreams();
//		Assert.assertEquals(streams.size(), 1);
	}
	
	 public static void main(String[] args) throws AppException {
		 TestBroadCastStreamService test= new TestBroadCastStreamService ();
	 test.testRegisterBroadcastStreamService();
	 }

}
