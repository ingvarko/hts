package com.hts.dao;

import java.util.List;

import com.hts.entities.Channel;
import com.hts.exceptions.AppException;

public interface IChannelDAO {
	Channel create(Channel channel) throws AppException;
	Channel getById(Integer id) throws AppException;
	void update(Channel channel) throws AppException;
	void delete(Channel channel) throws AppException;
	List<Channel> getAll() throws AppException;
	List<Channel> getByName(String name) throws AppException;
	Channel getByBroadcastStream(String broadcastStreamName)
			throws AppException;
}


