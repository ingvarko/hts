package com.hts.dao;

import java.util.List;

import com.hts.entity.SubscriptionPackage;
import com.hts.exceptions.AppException;

public interface ISubscriptionPackageDAO {
	SubscriptionPackage create(SubscriptionPackage subscriptionPackage) throws AppException;
	SubscriptionPackage getById(Integer id) throws AppException;
	
	void update(SubscriptionPackage subscriptionPackage) throws AppException;
	void delete(SubscriptionPackage subscriptionPackage) throws AppException;
	List<SubscriptionPackage> getAll() throws AppException;
	
}


