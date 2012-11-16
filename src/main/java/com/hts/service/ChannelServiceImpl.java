package com.hts.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hts.dao.ChannelDAOHibernateImpl;
import com.hts.dao.DAO;
import com.hts.entity.BroadcastStream;
import com.hts.entity.Channel;
import com.hts.entity.SubscriptionPackage;
import com.hts.exceptions.AppException;

public class ChannelServiceImpl implements IChannelService {
	final Logger log = Logger.getLogger(this.getClass());

	ChannelDAOHibernateImpl channelDAO = new ChannelDAOHibernateImpl();

	@Override
	public Channel create(String channelName, String broadcastStreamName) throws AppException {
		Channel h = channelDAO.create(new Channel(channelName, broadcastStreamName));
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

		Channel c = channelDAO.getById(id);
		DAO.close();
		return c;
	}

	@Override
	public List<Channel> getByName(String name) throws AppException {
		List<Channel> c = channelDAO.getByName(name);
		DAO.close();
		return c;
	}

	@Override
	public List<Channel> getAll() throws AppException {
		List<Channel> c = channelDAO.getAll();
		DAO.close();
		return c;
	}

	@Override
	public void addToSubscriptionPackage(Channel channel, SubscriptionPackage subscriptionPackage) throws AppException {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeFromSubscriptionPackage(Channel channel, SubscriptionPackage subscriptionPackage)
			throws AppException {
		// TODO Auto-generated method stub
	}

	@Override
	public Channel getByBroadcastName(String broadcastStreamName) throws AppException {
		Channel c = channelDAO.getByBroadcastStream(broadcastStreamName);
		DAO.close();
		return c;
	}

	@Override
	public List<Channel> getActive() throws AppException {
		BroadcastStreamServiceImpl broadcastStreamService = new BroadcastStreamServiceImpl();

		List<Channel> channels = channelDAO.getAll();
		ArrayList<Channel> activeChannels = new ArrayList<Channel>();

		for (Channel ch : channels) {
			List<BroadcastStream> broadcastStreams = broadcastStreamService.getActiveByName(ch.getBroadcastStream());
			if (!broadcastStreams.isEmpty()) {
				log.info("Found active channel: " + ch +"List<BroadcastStream>: " + broadcastStreams);
				activeChannels.add(ch);
			}

		}
		DAO.close();
		return activeChannels;
	}

}
