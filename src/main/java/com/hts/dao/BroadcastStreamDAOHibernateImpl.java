package com.hts.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import com.hts.entities.BroadcastStream;
import com.hts.exceptions.AppException;
import org.hibernate.Query;

public class BroadcastStreamDAOHibernateImpl extends DAO implements IBroadcastStreamDAO {
	final Logger log = Logger.getLogger(this.getClass());

	@Override
	/**
	 * Create BroadcastStrem content provider starts streaming RTMP.
	 * Example:
	 * gst-launch filesrc location=Benjamin.Button.2008.HDRip.avi ! \
	 * decodebin name=demux ! queue ! videorate ! videoscale method=0 ! \
	 *  video/x-raw-yuv,width=852,height=480,framerate=\(fraction\)24/1 ! \
	 *   ffmpegcolorspace ! x264enc pass=pass1 threads=0 bitrate=900 \
	 *   tune=zerolatency ! flvmux name=mux ! 
	 *   rtmpsink location="rtmp://localhost/oflaDemo/red5StreamDemo" demux. ! 
	 *   queue ! progressreport ! audioconvert ! audiorate ! audioresample ! 
	 *   faac bitrate=96000 ! audio/mpeg,mpegversion=4,stream-format=raw ! mux.
	 *   
	 *    Stream name would be set to red5StreamDemo
	 */
	public BroadcastStream create(BroadcastStream stream) throws AppException {
		try {
			begin();

			getSession().save(stream);
			commit();
			return stream;
		}
		catch (HibernateException e) {
			rollback();
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@Override
	public BroadcastStream getById(Integer streamId) throws AppException {
		try {
			begin();
			Query q = getSession().createQuery("from BroadcastStream s where s.id= :streamId");
			q.setString("streamId", streamId.toString());
			BroadcastStream stream = (BroadcastStream) q.uniqueResult();
			commit();
			return stream;
		}
		catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@Override
	/**
	 * During saving sets @UpdateDate to new Date(); automatically.
	 */
	public void update(BroadcastStream stream) throws AppException {
		try {
			 begin();
			stream.setUpdateDate(new Date());
			getSession().update(stream);
			 commit();
		}
		catch (HibernateException e) {
			rollback();
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}

	}

	@Override
	public void delete(BroadcastStream stream) throws AppException {
		try {
			 begin();
			getSession().delete(stream);
			 commit();
		}
		catch (HibernateException e) {
			rollback();
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BroadcastStream> getAll() throws AppException {
		try {
			begin();
			Query q = getSession().createQuery("from BroadcastStream");
			List<BroadcastStream> list = q.list();
			commit();
			return list;
		}
		catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BroadcastStream> list(int firstResult, int maxResults) throws AppException {
		try {
			begin();
			Query q = getSession().createQuery("from BroadcastStream");
			q.setFirstResult(firstResult);
			q.setMaxResults(maxResults);
			List<BroadcastStream> list = q.list();

			commit();
			return list;
		}
		catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BroadcastStream> getByName(String name) throws AppException {
		try {
			begin();
			Query q = getSession().createQuery("from BroadcastStream s where s.streamName= :sname");
			q.setString("sname", name.toString());
			List<BroadcastStream> list = q.list();
			commit();
			return list;
		}
		catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BroadcastStream> getActiveByName(String name) throws AppException {
		try {
			begin();
			Query q = getSession().createQuery("from BroadcastStream s where s.streamName= :sname and s.isActive=1");
			q.setString("sname", name.toString());
			List<BroadcastStream> list = q.list();

			commit();
			return list;
		}
		catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BroadcastStream> getAllActive() throws AppException {
		try {
			begin();
			Query q = getSession().createQuery("from BroadcastStream s where s.isActive=true");
			List<BroadcastStream> list = q.list();
			commit();

			return list;
		}
		catch (HibernateException e) {
			log.error(e);
			throw new AppException(e.getCause().getMessage());
		}
	}
}
