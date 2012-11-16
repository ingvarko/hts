package com.hts.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.json.JSONObject;

@Entity
@Table(name = "HOTEL")
public class Hotel {
	private Long uuId;
	private String hotelName;
	private String hotelAddr;

	public Hotel() {
	}

	public Hotel(String name) {
		this.setHotelName(name);
	}

	@Id
	@Column(name = "HOTEL_ID")
	public Long getUuId() {
		return uuId;
	}

	public void setUuId(Long uuId) {
		this.uuId = uuId;
	}

	@Column(name = "HOTEL_NAME", nullable = false)
	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	@Column(name = "HOTEL_ADDR")
	public String getHotelAddr() {
		return hotelAddr;
	}

	public void setHotelAddr(String hotelAddr) {
		this.hotelAddr = hotelAddr;
	}

	@Transient
	public String getJson() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("uuId", uuId.toString());
		map.put("hotelName", hotelName);
		map.put("hotelAddress", hotelAddr);

		JSONObject json = new JSONObject();
		json.accumulateAll((Map<String, String>) map);

		return json.toString();
	}
}
