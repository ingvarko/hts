package com.hts.service;

import java.net.UnknownHostException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hts.dao.DAO;
import com.hts.dao.IpAddressDAOHibernateImpl;
import com.hts.entities.Channel;
import com.hts.entities.IpAddress;
import com.hts.entities.Room;
import com.hts.entities.SubscriptionPackage;
import com.hts.exceptions.AppException;

public class IpAddressServiceImpl implements IIpAddressService {
	final Logger log = Logger.getLogger(this.getClass());
	IpAddressDAOHibernateImpl ipAddressDAO = new IpAddressDAOHibernateImpl();

	@Override
	public void update(IpAddress ipAddress) throws AppException {
		ipAddressDAO.update(ipAddress);
		DAO.close();
		log.info("updated ipAddress: " + ipAddress);

	}

	@Override
	public void delete(IpAddress ipAddress) throws AppException {
		if (ipAddress != null) {

			ipAddressDAO.delete(ipAddress);
			DAO.close();
			log.info("deleted ipAddress: " + ipAddress.getIpAddress());
		}

	}

	@Override
	public List<IpAddress> getByIp(String ipAddress) throws AppException {
		return ipAddressDAO.getByIp(ipAddress);
	}

	@Override
	public IpAddress getById(Integer id) throws AppException {
		return ipAddressDAO.getById(id);
	}

	@Override
	public IpAddress create(String name) throws UnknownHostException,
			AppException {

		IpAddress ipAddress = new IpAddress(name);

		ipAddressDAO.create(ipAddress);
		DAO.close();
		log.info("updated ipAddress: " + ipAddress.getIpAddress());
		return ipAddress;

	}

	@Override
	public boolean isBroadcastStreamAllowedForIP(String ipAddress,
			String broadcastStreamName) throws UnknownHostException,
			AppException {
		log.info("calling isBroadcastStreamAllowedForIP("+ipAddress+","+
			broadcastStreamName+");");
		
		List<IpAddress> ipAddresses = ipAddressDAO.getByIp(ipAddress);
		if (ipAddresses.size()==0)
			return false;
		
		IpAddress ipAddr = ipAddresses.get(0);
		
		Room room = ipAddr.getRoom();
		if (room==null)
			return false;
		SubscriptionPackage subscriptionPackage = room.getSubscriptionPackage();
		if (subscriptionPackage ==null)
			return false;
		
		List<Channel> chanels = (List<Channel>) subscriptionPackage
				.getChannels();
		Channel channel = new ChannelServiceImpl()
				.getByBroadcastName(broadcastStreamName);
		return chanels.contains(channel);

	}

}
