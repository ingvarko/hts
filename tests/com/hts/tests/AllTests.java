package com.hts.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestBroadCastStreamService.class, TestHotelService.class,
		TestIpAddressService.class, TestRoomService.class,
		TestSubscriptionPackageService.class, TestChannelService.class })
public class AllTests {
}