package com.hts.dao;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DAO {

	final Logger log = Logger.getLogger(this.getClass());
	
	
	private static final ThreadLocal<Session> session = new ThreadLocal<Session>();
	@SuppressWarnings("deprecation")
	private static final SessionFactory sessionFactory = new Configuration()
			.configure().buildSessionFactory();
	
	protected DAO() {
	}

	public static Session getSession() {
		Session session = (Session) DAO.session.get();
		if (session == null) {
			session = sessionFactory.openSession();
			DAO.session.set(session);
		}
		return session;
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
		DAO.session.set(null);
	}

	public static void close() {
		getSession().close();
		DAO.session.set(null);
	}
}