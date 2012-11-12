package com.hts.service;

import java.net.UnknownHostException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hts.dao.DAO;
import com.hts.dao.IpAddressDAOHibernateImpl;
import com.hts.dao.SubscriptionPackageDAOHibernateImpl;
import com.hts.entity.Channel;
import com.hts.entity.IpAddress;
import com.hts.entity.Room;
import com.hts.entity.SubscriptionPackage;
import com.hts.exceptions.AppException;

public class IpAddressServiceImpl implements IIpAddressService {
	final Logger log = Logger.getLogger(this.getClass());

	IpAddressDAOHibernateImpl ipAddressDAO = new IpAddressDAOHibernateImpl();
	SubscriptionPackageDAOHibernateImpl subscriptionPackageDAO = new SubscriptionPackageDAOHibernateImpl();

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
	public IpAddress getByIp(String ipAddress) throws AppException {
		return ipAddressDAO.getByIp(ipAddress);
	}

	@Override
	public IpAddress getById(Integer id) throws AppException {
		return ipAddressDAO.getById(id);
	}

	@Override
	public IpAddress create(String name) throws UnknownHostException, AppException {

		IpAddress ipAddress = new IpAddress(name);

		ipAddressDAO.create(ipAddress);
		DAO.close();
		log.info("created ipAddress: " + ipAddress.getIpAddress());
		return ipAddress;

	}

	@Override
	public boolean isBroadcastStreamAllowedForIP(String ipAddress, String broadcastStreamName)
			throws UnknownHostException, AppException {
		log.info("Calling isBroadcastStreamAllowedForIP(" + ipAddress + "," + broadcastStreamName + ");");

		IpAddress ipAddr= ipAddressDAO.getByIp(ipAddress);
		if (ipAddr == null)
			return false;

		Room room = ipAddr.getRoom();
		if (room == null)
			return false;
		SubscriptionPackage subscriptionPackage = new SubscriptionPackageDAOHibernateImpl().getById(room.getSubscriptionPackage().getId()); 
		if (subscriptionPackage == null)
			return false;
		
		List<Channel> channels = (List<Channel>) subscriptionPackage.getChannels();
		
		Channel channel = new ChannelServiceImpl().getByBroadcastName(broadcastStreamName);
		if (channel ==null){
			//TODO return false
			log.info("Access to Channel allowed");
			return true;
		}
		
		//doesn;t work here: return channels.contains(channel);
		//workaround for Object.equal:
		for(Channel c : channels){
			if (c.getId()==channel.getId()){
				return true;
			}
		}
		
		return false;
	}
}
