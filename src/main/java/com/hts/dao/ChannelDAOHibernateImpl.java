package com.hts.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.hts.entities.Channel;
import com.hts.exceptions.AppException;

public class ChannelDAOHibernateImpl extends DAO implements IChannelDAO {
	final Logger log = Logger.getLogger(this.getClass());

	@Override
	public Channel create(Channel channel) throws AppException {
		try {
			begin();
			getSession().save(channel);
			commit();
			return channel;
		} catch (HibernateException e) {
			rollback();
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Channel> getAll() throws AppException {
		try {
			Query q = getSession().createQuery("from Chanel ");
			List<Channel> channels = q.list();
			return channels;
		} catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@Override
	public Channel getById(Integer id) throws AppException {
		try {
			Query q = getSession()
					.createQuery("from Channel h where h.Id= :id");
			q.setLong("id", id);
			Channel channel = (Channel) q.uniqueResult();
			return channel;
		} catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@Override
	public Channel getByBroadcastStream(String broadcastStreamName) throws AppException {
		try {
			Query q = getSession()
					.createQuery("from Channel h where h.broadcastStreamName= :broadcastStreamName");
			q.setString("broadcastStreamName", broadcastStreamName);
			Channel channel = (Channel) q.uniqueResult();
			return channel;
		} catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@Override
	public List<Channel> getByName(String name) throws AppException {
		try {
			Query q = getSession()
					.createQuery("from Channel h where h.channelName= :name");
			q.setString("name", name);
			@SuppressWarnings("unchecked")
			List<Channel> channels = q.list();
			return channels;
		} catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@Override
	// update
	public void update(Channel channel) throws AppException {
		try {
			begin();
			getSession().update(channel);
			commit();
		} catch (HibernateException e) {
			rollback();
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}

	}

	@Override
	public void delete(Channel channel) throws AppException {
		try {
			begin();
			getSession().delete(channel);
			commit();
		} catch (HibernateException e) {
			rollback();
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}
}
