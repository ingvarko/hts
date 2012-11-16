package com.hts.dao;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.hts.entity.Hotel;
import com.hts.exceptions.AppException;

public class HotelDAOHibernateImpl extends DAO implements IHotelDAO {
	final Logger log = Logger.getLogger(this.getClass());

	@Override
	public Hotel create(Hotel hotel) throws AppException {
		try {
			begin();
			hotel.setUuId(UUID.randomUUID().getMostSignificantBits());
			getSession().save(hotel);
			commit();
			return hotel;
		} catch (HibernateException e) {
			rollback();
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Hotel> getAll() throws AppException {
		try {
			begin();
			Query q = getSession().createQuery(
					"from Hotel h");
			List<Hotel> hotels= q.list();
			commit();
			return hotels;
		} catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Hotel> getByName(String name) throws AppException {
		try {
			begin();
			Query q = getSession().createQuery(
					"from Hotel h where h.hotelName= :name");
			q.setString("name", name);
			List<Hotel> hotels= q.list();
			commit();

			return hotels;
		} catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}
	
	@Override
	public Hotel getByUuId(Long uuId) throws AppException {
		try {
			begin();
			Query q = getSession().createQuery(
					"from Hotel h where h.uuId= :uuId");
			q.setLong("uuId", uuId);
			Hotel hotel= (Hotel) q.uniqueResult();
			commit();

			return hotel;
		} catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@Override
	//update
	public void update(Hotel hotel) throws AppException {
		// TODO throw exception if uuId is empty
		try {
//			begin();
			throw new AppException("check uuId is not empty");
//			getSession().update(hotel);
//			commit();
		} catch (HibernateException e) {
			rollback();
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}

	}

	@Override
	public void delete(Hotel hotel) throws AppException {
		try {
			begin();
			getSession().delete(hotel);
			commit();
		} catch (HibernateException e) {
			rollback();
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}
}

