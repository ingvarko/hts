package com.hts.dao;

import java.util.List;

import com.hts.entity.Room;
import com.hts.exceptions.AppException;

public interface IRoomDAO {
	Room create(Room room) throws AppException;
	Room getById(Integer Id) throws AppException;
	void update(Room room) throws AppException;
	void delete(Room room) throws AppException;
	List<Room> getAll() throws AppException;
	List<Room> getByName(String roomName) throws AppException;
}


