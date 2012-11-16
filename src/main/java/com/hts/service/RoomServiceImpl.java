package com.hts.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.hts.dao.DAO;
import com.hts.dao.HotelDAOHibernateImpl;
import com.hts.dao.RoomDAOHibernateImpl;
import com.hts.entity.Hotel;
import com.hts.entity.Room;
import com.hts.entity.SubscriptionPackage;
import com.hts.exceptions.AppException;

public class RoomServiceImpl implements IRoomService {
	final Logger log = Logger.getLogger(this.getClass());
	RoomDAOHibernateImpl roomDAO = new RoomDAOHibernateImpl();
	HotelDAOHibernateImpl hotelDAO = new HotelDAOHibernateImpl();

	public Room create(String name) throws AppException {

		Hotel hotel = hotelDAO.getAll().get(0);
//		Hotel hotel=hotels.get(0);
//		.get(1);

		Room r = roomDAO.create(new Room(name, hotel));
		DAO.close();
		log.info("created room: " + r);
		return r;
	}

	@Override
	public Room create(String name, Hotel hotel) throws AppException {

		if (hotel == null) 
			throw new AppException("Hotel must not be null. Use RoomServiceImpl.create(String str).");

		Room r = roomDAO.create(new Room(name, hotel));
		DAO.close();
		log.info("created room: " + r);
		return r;
	}

	@Override
	public void update(Room room) throws AppException {
		roomDAO.update(room);
		DAO.close();
		log.info("updated room: " + room);
	}

	@Override
	public void delete(Room room) throws AppException {
		roomDAO.delete(room);
		DAO.close();
		log.info("deleted room: " + room);
	}

	@Override
	public List<Room> getByName(String name) throws AppException {
		return roomDAO.getByName(name);
	}

	@Override
	public Room getById(Integer id) throws AppException {
		return roomDAO.getById(id);
	}

	@Override
	public void addSubscriptionPackage(Room room, SubscriptionPackage subscriptionPackage)
			throws AppException {

		room.setSubscriptionPackage(subscriptionPackage);
		
		roomDAO.update(room);
		log.info("added subscriptionPackage to room: " + room.toString());
		
	}

	@Override
	public void removeSubscriptionPackage(Room room)
			throws AppException {
		room.setSubscriptionPackage(null);
		
		roomDAO.update(room);
		log.info("removed subscriptionPackage from room: " + room.toString());
		
	}

}
