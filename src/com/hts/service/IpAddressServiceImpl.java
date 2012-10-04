package com.hts.service;

import java.net.UnknownHostException;
import java.util.List;

import org.apache.log4j.Logger;

import com.hts.dao.DAO;
import com.hts.dao.IpAddressDAOHibernateImpl;
import com.hts.entities.IpAddress;
import com.hts.exceptions.AppException;

public class IpAddressServiceImpl implements IIpAddressService {
	final Logger log = Logger.getLogger(this.getClass());
	IpAddressDAOHibernateImpl ipAddressDAO = new IpAddressDAOHibernateImpl();

	@Override
	public void update(IpAddress ipAddress) throws AppException {
		ipAddressDAO.save(ipAddress);
		DAO.close();
		log.info("updated ipAddress: " + ipAddress);

	}

	@Override
	public void delete(IpAddress ipAddress) throws AppException {
		ipAddressDAO.delete(ipAddress);
		DAO.close();
		log.info("deleted ipAddress: " + ipAddress);

	}

	@Override
	public List<IpAddress> getByName(String name) throws AppException {
		return ipAddressDAO.getByName(name);
	}

	@Override
	public IpAddress getById(Integer id) throws AppException {
		return ipAddressDAO.getById(id);
	}

	@Override
	public IpAddress create(String name) throws UnknownHostException, AppException {
		
		IpAddress ipAddress = new IpAddress(name);
		
		ipAddressDAO.save(ipAddress);
		DAO.close();
		log.info("updated ipAddress: " + ipAddress.getIpAddress());
		return ipAddress;
	
	}

	// public IpAddress create(String name) throws AppException {
	//
	// Hotel hotel = hotelDAO.getAll().get(0);
	//
	// IpAddress r = ipAddressDAO.create(new IpAddress(name, hotel));
	// DAO.close();
	// log.info("created ipAddress: " + r);
	// return r;
	// }
	//
	// @Override
	// public IpAddress create(String name, Hotel hotel) throws AppException {
	//
	// if (hotel == null)
	// throw new
	// AppException("Hotel must not be null. Use IpAddressServiceImpl.create(String str).");
	//
	// IpAddress r = ipAddressDAO.create(new IpAddress(name, hotel));
	// DAO.close();
	// log.info("created ipAddress: " + r);
	// return r;
	// }
	//
	// @Override
	// public void update(IpAddress ipAddress) throws AppException {
	// ipAddressDAO.save(ipAddress);
	// DAO.close();
	// log.info("updated ipAddress: " + ipAddress);
	// }
	//
	// @Override
	// public void delete(IpAddress ipAddress) throws AppException {
	// ipAddressDAO.delete(ipAddress);
	// DAO.close();
	// log.info("deleted ipAddress: " + ipAddress);
	// }
	//
	// @Override
	// public List<IpAddress> getByName(String name) throws AppException {
	// return ipAddressDAO.getByName(name);
	// }
	//
	// @Override
	// public IpAddress getById(Integer id) throws AppException {
	// return ipAddressDAO.getById(id);
	// }

}
