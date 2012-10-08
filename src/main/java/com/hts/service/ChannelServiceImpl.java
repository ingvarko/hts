package com.hts.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.hts.dao.ChannelDAOHibernateImpl;
import com.hts.dao.DAO;
import com.hts.entities.Channel;
import com.hts.entities.SubscriptionPackage;
import com.hts.exceptions.AppException;

public class ChannelServiceImpl implements IChannelService {
	final Logger log = Logger.getLogger(this.getClass());
	ChannelDAOHibernateImpl channelDAO = new ChannelDAOHibernateImpl();

	@Override
	public Channel create(String channelName, String broadcastStreamName)
			throws AppException {
		Channel h = channelDAO.create(new Channel(channelName,
				broadcastStreamName));
		DAO.close();
		log.info("created channel: " + h);
		return h;
	}

	@Override
	public void update(Channel channel) throws AppException {
		channelDAO.update(channel);
		DAO.close();
		log.info("updated channel: " + channel);
	}

	@Override
	public void delete(Channel channel) throws AppException {
		channelDAO.delete(channel);
		DAO.close();
		log.info("deleted channel: " + channel);
	}

	@Override
	public Channel getById(Integer id) throws AppException {
		return channelDAO.getById(id);
	}

	@Override
	public List<Channel> getByName(String name) throws AppException {
		return channelDAO.getByName(name);
	}

	@Override
	public List<Channel> getAll() throws AppException {
		return channelDAO.getAll();
	}

	@Override
	public void addToSubscriptionPackage(Channel channel,
			SubscriptionPackage subscriptionPackage) throws AppException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFromSubscriptionPackage(Channel channel,
			SubscriptionPackage subscriptionPackage) throws AppException {
		// TODO Auto-generated method stub

	}

	@Override
	public Channel getByBroadcastName(String broadcastStreamName) throws AppException {
		return channelDAO.getByBroadcastStream(broadcastStreamName);
	}

}
