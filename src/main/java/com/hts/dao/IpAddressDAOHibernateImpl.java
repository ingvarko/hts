package com.hts.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.hts.entities.IpAddress;
import com.hts.exceptions.AppException;

public class IpAddressDAOHibernateImpl extends DAO implements IIpAddressDAO {
	final Logger log = Logger.getLogger(this.getClass());

	@Override
	public IpAddress create(IpAddress ipAddress) throws AppException {
		try {
			begin();
			getSession().save(ipAddress);
			commit();

			return ipAddress;
		}
		catch (HibernateException e) {
			rollback();
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@Override
	public IpAddress getById(Integer id) throws AppException {
		try {
			begin();
			Query q = getSession().createQuery("from IpAddress r where r.id= :id");
			q.setInteger("id", id);
			IpAddress ipAddress = (IpAddress) q.uniqueResult();
			commit();

			return ipAddress;
		}
		catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@Override
	public void update(IpAddress ipAddress) throws AppException {
		try {
			begin();
			getSession().update(ipAddress);

			commit();
		}
		catch (HibernateException e) {
			rollback();
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@Override
	public void delete(IpAddress ipAddress) throws AppException {
		try {
			begin();
			IpAddress ipAddress1 = (IpAddress) getSession().merge(ipAddress);
			getSession().delete(ipAddress1);

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
	public List<IpAddress> getAll() throws AppException {
		try {
			begin();
			Query q = getSession().createQuery("from IpAddress");
			List<IpAddress> ipAddress = q.list();
			commit();

			return ipAddress;
		}
		catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@Override
	public IpAddress getByIp(String ipAddress) throws AppException {
		try {
			begin();
			Query q = getSession().createQuery("from IpAddress r where r.ipAddress= :ipAddress");
			q.setString("ipAddress", ipAddress);
			IpAddress ipAddr = (IpAddress) q.uniqueResult();
			commit();
			if (ipAddr != null) {
				Hibernate.initialize(ipAddr);
				if (ipAddr.getRoom() != null) {
					Hibernate.initialize(ipAddr.getRoom());
					if (ipAddr.getRoom().getSubscriptionPackage() != null)
						Hibernate.initialize(ipAddr.getRoom().getSubscriptionPackage());
				}
			}
			return ipAddr;
		}
		catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}
}
