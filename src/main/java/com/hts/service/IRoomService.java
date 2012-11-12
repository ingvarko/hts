package com.hts.service;

import java.util.List;

import com.hts.entity.Hotel;
import com.hts.entity.Room;
import com.hts.entity.SubscriptionPackage;
import com.hts.exceptions.AppException;

public interface IRoomService {

	void update(Room room) throws AppException;
	Room getById(Integer uuid) throws AppException;
	void delete(Room room) throws AppException;
	List<Room> getByName(String name) throws AppException;
	Room create(String name, Hotel hotel) throws AppException;
	Room create(String name) throws AppException;
	
	void addSubscriptionPackage(Room room, SubscriptionPackage subscriptionPackage)throws AppException;
	void removeSubscriptionPackage(Room room)throws AppException;
	
}
