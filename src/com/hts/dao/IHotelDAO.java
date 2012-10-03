package com.hts.dao;

import java.util.List;

import com.hts.entities.Hotel;
import com.hts.exceptions.AppException;

public interface IHotelDAO {
	Hotel create(Hotel hotel) throws AppException;
	Hotel getByUuId(Long uuId) throws AppException;
	void save(Hotel hotel) throws AppException;
	void delete(Hotel hotel) throws AppException;
	List<Hotel> getAll() throws AppException;
	List<Hotel> getByName(String name) throws AppException;
}


