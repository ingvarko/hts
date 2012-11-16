package com.hts.service;

import java.util.List;

import com.hts.entity.Channel;
import com.hts.entity.SubscriptionPackage;
import com.hts.exceptions.AppException;

public interface ISubscriptionPackageService {

	void update(SubscriptionPackage subscriptionPackage) throws AppException;
	SubscriptionPackage getById(Integer id) throws AppException;
	void delete(SubscriptionPackage subscriptionPackage) throws AppException;
	List<SubscriptionPackage> getByName(String name) throws AppException;
	SubscriptionPackage create(String name) throws AppException;
	List<SubscriptionPackage> getAll() throws AppException ;
	
	void addChannel (SubscriptionPackage subscriptionPackage, Channel channel)throws AppException;
	void removeChannel(SubscriptionPackage subscriptionPackage, Channel channel)throws AppException;
	void removeAllChannels(SubscriptionPackage subscriptionPackage)throws AppException;
	
	
}
