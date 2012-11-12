package com.hts.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.hts.dao.DAO;
import com.hts.dao.HotelDAOHibernateImpl;
import com.hts.entity.Hotel;
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
		hotelDAO.update(hotel);
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

	@Override
	public String getJson(List<Hotel> list, String currentPage) {
		/**
		 * Json header spec
		 * 
		 * total total pages for the pager page current page for the pager
		 * records total number of records in the result set rows an array that
		 * contains the actual data id the unique id of the row cell an array
		 * that contains the data for a row
		 */
		Map<String, String> map = new HashMap<String, String>();
		JSONObject json = new JSONObject();

		Integer records = list.size();
		Integer totalPages = records / IJsonService.PAGESIZE + 1;

		map.put("total", totalPages.toString());
		map.put("page", currentPage);
		map.put("records", records.toString());

		List<String> rows = new ArrayList<String>();
		for (Hotel h : list) {
			rows.add( h.getJson());
		}

		map.put("rows",rows.toString());

		json.accumulateAll(map);
		return json.toString();
	}

}
