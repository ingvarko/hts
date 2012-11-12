package com.hts.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.hts.entity.Room;
import com.hts.exceptions.AppException;

public class RoomDAOHibernateImpl extends DAO implements IRoomDAO {
	final Logger log = Logger.getLogger(this.getClass());

	@Override
	public Room create(Room room) throws AppException {
		try {
			begin();
			getSession().save(room);
			commit();
			return room;
		}
		catch (HibernateException e) {
			rollback();
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@Override
	public Room getById(Integer id) throws AppException {
		try {
			begin();
			Query q = getSession().createQuery("from Room r where r.id= :id");
			q.setInteger("id", id);
			Room room = (Room) q.uniqueResult();
			commit();
			Hibernate.initialize(room);

			return room;
		}
		catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@Override
	public void update(Room room) throws AppException {
		try {
			begin();
			getSession().update(room);
			commit();
		}
		catch (HibernateException e) {
			rollback();
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@Override
	public void delete(Room room) throws AppException {
		try {
			begin();
			getSession().delete(room);
			commit();
		}
		catch (HibernateException e) {
			rollback();
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getAll() throws AppException {
		try {
			begin();
			Query q = getSession().createQuery("from Room");
			List<Room> rooms = q.list();
			commit();
			for (Room r : rooms) {
				Hibernate.initialize(r);
			}
			return rooms;
		}
		catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getByName(String roomName) throws AppException {
		try {
			begin();
			Query q = getSession().createQuery("from Room r where r.roomName= :name");
			q.setString("name", roomName);
			List<Room> rooms = q.list();
			commit();
			for (Room r : rooms) {
				Hibernate.initialize(r);
			}

			return rooms;
		}
		catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}
}
