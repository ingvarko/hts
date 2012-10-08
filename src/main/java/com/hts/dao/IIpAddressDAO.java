package com.hts.dao;

import java.util.List;

import com.hts.entities.IpAddress;
import com.hts.exceptions.AppException;

public interface IIpAddressDAO {
	IpAddress create(IpAddress ipAddress) throws AppException;
//	IpAddress create(IpAddress ipAddress, Room room) throws AppException;
	
	IpAddress getById(Integer Id) throws AppException;
	void update(IpAddress ipAddress) throws AppException;
	void delete(IpAddress ipAddress) throws AppException;
	List<IpAddress> getAll() throws AppException;
	List<IpAddress> getByIp(String ipAddress) throws AppException;
}


