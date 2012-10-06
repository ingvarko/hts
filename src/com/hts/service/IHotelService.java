package com.hts.service;

import java.util.List;

import com.hts.entities.Hotel;
import com.hts.exceptions.AppException;

public interface IHotelService extends IJsonService {

	Hotel create(String name) throws AppException;
	void update(Hotel hotel) throws AppException;
	Hotel getByUuId(Long uuid) throws AppException;
	void delete(Hotel hotel) throws AppException;
	
	List<Hotel> getByName(String name) throws AppException;
	List<Hotel> getAll() throws AppException;

	String getJson(List<Hotel> list, String currentPage);
		
}
