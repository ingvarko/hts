package com.hts.dao;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.hts.filter.HibernateUtil;

public class DAO {

	final Logger log = Logger.getLogger(this.getClass());
	 
	protected DAO() {
	}

	public static Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	protected void begin() {
		getSession().beginTransaction();
	}

	protected void commit() {
		getSession().getTransaction().commit();
	}

	protected void rollback() {
		try {
			getSession().getTransaction().rollback();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}
		try {
			getSession().close();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Closes session. Set Session to null
	 */
	public static void close() {
		getSession().close();
	}
}