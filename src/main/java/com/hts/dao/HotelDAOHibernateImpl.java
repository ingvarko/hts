package com.hts.dao;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.hts.entities.Hotel;
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
			Query q = getSession().createQuery(
					"from Hotel h");
			List<Hotel> hotels= q.list();
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
			Query q = getSession().createQuery(
					"from Hotel h where h.hotelName= :name");
			q.setString("name", name);
			List<Hotel> hotels= q.list();
			return hotels;
		} catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}
	
	@Override
	public Hotel getByUuId(Long uuId) throws AppException {
		try {
			Query q = getSession().createQuery(
					"from Hotel h where h.uuId= :uuId");
			q.setLong("uuId", uuId);
			Hotel hotel= (Hotel) q.uniqueResult();
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

/*
 * @Override public BroadcastStream create(BroadcastStream stream) throws
 * AppException { try { begin();
 * 
 * getSession().save(stream); commit(); return stream; } catch
 * (HibernateException e) { rollback(); log.error(e); throw new
 * AppException(e.getCause().getMessage()); } }
 * 
 * @Override public BroadcastStream getById(Integer streamId) throws
 * AppException { try { Query q = getSession().createQuery(
 * "from BroadcastStream s where s.id= :streamId"); q.setString("streamId",
 * streamId.toString()); BroadcastStream stream = (BroadcastStream)
 * q.uniqueResult(); return stream; } catch (HibernateException e) {
 * log.error(e); throw new AppException(e.getCause().getMessage()); } }
 * 
 * @Override public void save(BroadcastStream stream) throws AppException { try
 * { begin(); stream.setUpdateDate(new Date()); getSession().update(stream);
 * commit(); } catch (HibernateException e) { rollback(); log.error(e); throw
 * new AppException(e.getCause().getMessage()); }
 * 
 * }
 * 
 * @Override public void delete(BroadcastStream stream) throws AppException {
 * try { begin(); getSession().delete(stream); commit(); } catch
 * (HibernateException e) { rollback(); log.error(e); throw new
 * AppException(e.getCause().getMessage()); } }
 * 
 * @Override
 * 
 * @SuppressWarnings("unchecked") public List<BroadcastStream> list() throws
 * AppException { try { Query q =
 * getSession().createQuery("from BROADCASTSTREAM"); List<BroadcastStream> list
 * = q.list(); return list; } catch (HibernateException e) { log.error(e); throw
 * new AppException(e.getCause().getMessage()); } }
 * 
 * @Override
 * 
 * @SuppressWarnings("unchecked") public List<BroadcastStream> list(int
 * firstResult, int maxResults) throws AppException { try { Query q =
 * getSession().createQuery("from BROADCASTSTREAM");
 * q.setFirstResult(firstResult); q.setMaxResults(maxResults);
 * List<BroadcastStream> list = q.list();
 * 
 * // System.out.println(list.size()); return list; } catch (HibernateException
 * e) { log.error(e); throw new AppException(e.getCause().getMessage()); }
 * 
 * }
 * 
 * @SuppressWarnings("unchecked")
 * 
 * @Override public List<BroadcastStream> getByName(String name) throws
 * AppException { try { Query q
 * =getSession().createQuery("from BroadcastStream s where s.streamName= :sname"
 * ); q.setString("sname", name.toString()); List<BroadcastStream> list =
 * q.list();
 * 
 * // System.out.println(list.size()); return list; } catch (HibernateException
 * e) { log.error(e); throw new AppException(e.getCause().getMessage()); }
 * 
 * }
 */