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

	static BroadcastStreamDAOHibernateImpl broadcastStreamDAO = new BroadcastStreamDAOHibernateImpl();

	@Override
	public BroadcastStream create(String name) throws AppException {

		BroadcastStream stream = new BroadcastStream(name);
		stream.setPublishedDate(new Date());
		stream.setUpdateDate(stream.getPublishedDate());
		stream.setStatus(BroadcastStream.ACTIVE);

		broadcastStreamDAO.create(stream);
		DAO.close();
		log.info("registerBroadcastStream:" + stream);
		return stream;
	}

	@Override
	public void delete(BroadcastStream broadcastStream) throws AppException {

		broadcastStreamDAO.delete(broadcastStream);
		DAO.close();
		log.info("deleted BroadcastStream:" + broadcastStream.getName());
	}

	/**
	 * Finds all streams by given name which could remains from previous server starts. Set in all streams @unpublishedDate
	 * to new Date() and @status to inactive.
	 */
	@Override
	public void unregisterBroadcastStream(String broadcastStreamName) throws AppException {
		List<BroadcastStream> list = broadcastStreamDAO.getActiveByName(broadcastStreamName);

		for (BroadcastStream str : list) {
			setInactive(str);
			broadcastStreamDAO.update(str);
			log.info("unregisterBroadcastStream:" + str);
		}
		DAO.close();
	}

	private void setInactive(BroadcastStream str) {
		str.setUpdateDate(new Date());
		str.setUnpublishedDate(new Date());
		str.setStatus(BroadcastStream.INACTIVE);
	}

	@Override
	public void unregisterAllActiveBroadcastStreams() throws AppException {
		List<BroadcastStream> list = broadcastStreamDAO.getAllActive();

		for (BroadcastStream str : list) {
			setInactive(str);
			broadcastStreamDAO.update(str);
			log.info("unregisterBroadcastStream:" + str);
		}
	}

	@Override
	public List<BroadcastStream> getAllBroadcastStreams() throws AppException {
		List<BroadcastStream> list = broadcastStreamDAO.getAll();
		DAO.close();
		log.info("getAllBroadcastStreams:" + list);
		return list;
	}

	@Override
	public BroadcastStream getById(Integer streamId) throws AppException {
		BroadcastStream b = broadcastStreamDAO.getById(streamId);
		DAO.close();
		return b;
	}

	@Override
	public List<BroadcastStream> getByName(String name) throws AppException {
		List<BroadcastStream> b = broadcastStreamDAO.getByName(name);
		DAO.close();
		return b;
	}

	@Override
	public List<BroadcastStream> getActiveByName(String name) throws AppException {
		List<BroadcastStream> b = broadcastStreamDAO.getActiveByName(name);
		DAO.close();
		return b;
	}
}
