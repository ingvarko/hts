package com.hts.service;

import java.util.List;

import com.hts.entities.Hotel;
import com.hts.entities.Room;
import com.hts.exceptions.AppException;

public interface IRoomService {

	void update(Room room) throws AppException;
	Room getById(Integer uuid) throws AppException;
	void delete(Room room) throws AppException;
	List<Room> getByName(String name) throws AppException;
	Room create(String name, Hotel hotel) throws AppException;
	Room create(String name) throws AppException;
}
