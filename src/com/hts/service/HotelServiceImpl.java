package com.hts.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.hts.dao.DAO;
import com.hts.dao.HotelDAOHibernateImpl;
import com.hts.entities.Hotel;
import com.hts.exceptions.AppException;

public class HotelServiceImpl implements IHotelService {
	final Logger log = Logger.getLogger(this.getClass());
	HotelDAOHibernateImpl hotelDAO = new HotelDAOHibernateImpl();

	@Override
	public Hotel create(String name) throws AppException {
		Hotel h = hotelDAO.create(new Hotel(name));
		DAO.close();
		log.info("created hotel: " + h);
		return h;
	}

	@Override
	public void update(Hotel hotel) throws AppException {
		hotelDAO.save(hotel);
		DAO.close();
		log.info("updated hotel: " + hotel);
	}
	
	@Override
	public void delete(Hotel hotel) throws AppException {
		hotelDAO.delete(hotel);
		DAO.close();
		log.info("deleted hotel: " + hotel);
	}

	@Override
	public Hotel getByUuId(Long uuId) throws AppException {
		return hotelDAO.getByUuId(uuId);
	}
	
	@Override
	public List<Hotel> getByName(String name) throws AppException {
		return hotelDAO.getByName(name);
	}

	@Override
	public List<Hotel> getAll() throws AppException {
		return hotelDAO.getAll();
	}

}
