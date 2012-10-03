package com.hts.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HOTEL")
public class Hotel {

	public Hotel() {
	}

	public Hotel(String name) {
		this.setHotelName(name);
	}

	@Id
	@Column(name = "HOTEL_ID")
	private Long uuId;

	@Column(name = "HOTEL_NAME")
	private String hotelName;
	
	@Column(name = "HOTEL_ADDR")
	private String hotelAddr;

	public Long getUuId() {
		return uuId;
	}

	public void setUuId(Long uuId) {
		this.uuId = uuId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelAddr() {
		return hotelAddr;
	}

	public void setHotelAddr(String hotelAddr) {
		this.hotelAddr = hotelAddr;
	}

}
