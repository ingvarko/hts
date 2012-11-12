package com.hts.service;

import java.util.List;

import com.hts.entity.Channel;
import com.hts.entity.SubscriptionPackage;
import com.hts.exceptions.AppException;

public interface IChannelService {

	void update(Channel channel) throws AppException;
	Channel getById(Integer id) throws AppException;
	Channel getByBroadcastName(String broadcastName) throws AppException;
	void delete(Channel channel) throws AppException;
	List<Channel> getAll() throws AppException;
	List<Channel> getByName(String name) throws AppException;
	List<Channel> getActive() throws AppException;
	
	Channel create(String name, String broadcastStreamName) throws AppException;
	
	void addToSubscriptionPackage (Channel channel, SubscriptionPackage subscriptionPackage)throws AppException;
	void removeFromSubscriptionPackage(Channel channel, SubscriptionPackage subscriptionPackage)throws AppException;
	
}
