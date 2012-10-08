package com.hts.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HOTEL")
public class NetworkAddress {
	private Long uuId;
	private String hotelName;
	private String hotelAddr;

	public NetworkAddress() {
	}

	public NetworkAddress(String name) {
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
}
