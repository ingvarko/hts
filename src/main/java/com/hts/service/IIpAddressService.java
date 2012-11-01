package com.hts.service;

import java.net.UnknownHostException;

import com.hts.entities.IpAddress;
import com.hts.exceptions.AppException;

public interface IIpAddressService {

	void update(IpAddress ipAddress) throws AppException;
	IpAddress getById(Integer uuid) throws AppException;
	void delete(IpAddress ipAddress) throws AppException;
	IpAddress getByIp(String name) throws AppException;
	IpAddress create(String name) throws UnknownHostException, AppException;
	
	boolean isBroadcastStreamAllowedForIP(String ipAddress,
			String broadcastStreamName) throws UnknownHostException,
			AppException;
}
