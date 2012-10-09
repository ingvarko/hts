package com.hts.service;

import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

import com.hts.dao.BroadcastStreamDAOHibernateImpl;
import com.hts.dao.DAO;
import com.hts.entities.BroadcastStream;
import com.hts.exceptions.AppException;

public class BroadcastStreamServiceImpl implements IBroadcastStreamService {
	final Logger log = Logger.getLogger(this.getClass());
	static BroadcastStreamDAOHibernateImpl serviceDAO = new BroadcastStreamDAOHibernateImpl();

	@Override
	public BroadcastStream create (String name)
			throws AppException {

		BroadcastStream stream = new BroadcastStream(name);
		stream.setPublishedDate(new Date());
		stream.setUpdateDate(stream.getPublishedDate());
		stream.setStatus(BroadcastStream.ACTIVE);

		serviceDAO.create(stream);
		DAO.close();
		log.info("registerBroadcastStream:" + stream);
		return stream;
	}

	@Override
	public void delete (BroadcastStream broadcastStream)
			throws AppException {

		serviceDAO.delete(broadcastStream);
		DAO.close();
		log.info("deleted BroadcastStream:" + broadcastStream.getName());
	}

	/**
	 * Finds all streams by given name which could remains from previous server starts.
	 * Set in all streams @unpublishedDate to new Date() and @status to inactive.
	 */
	@Override
	public void  unregisterBroadcastStream(String broadcastStreamName)
			throws AppException {
		List <BroadcastStream> list = serviceDAO.getByName(broadcastStreamName);
		
		for (BroadcastStream str : list) {
			str.setUpdateDate(new Date());
			str.setUnpublishedDate(new Date());
			str.setStatus(BroadcastStream.INACTIVE);

			serviceDAO.update(str);
		}
		
		DAO.close();
		log.info("unregisterBroadcastStream:" + broadcastStreamName);
	}

	@Override
	public List<BroadcastStream> getAllBroadcastStreams() throws AppException {
		List<BroadcastStream> list = serviceDAO.getAll();
		DAO.close();
		log.info("getAllBroadcastStreams:" + list);
		return list;
	}

	@Override
	public BroadcastStream getById(Integer streamId)
			throws AppException {
		return serviceDAO.getById(streamId);
	}

	@Override
	public List<BroadcastStream> getByName(String name)
			throws AppException {
		return serviceDAO.getByName(name);
	}

	@Override
	public void allowRoomAccess() throws AppException {
		// TODO Auto-generated method stub

	}

	@Override
	public void withdrawRoomAccess() throws AppException {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectRoom() throws AppException {
		// TODO Auto-generated method stub

	}

	@Override
	public void disconnectRoom() throws AppException {
		// TODO Auto-generated method stub

	}

}
