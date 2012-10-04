package com.hts.entities;

import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "IPADDRESS")
public class IpAddress {

	private Integer id;
	private String ipAddress;

	public IpAddress() {
	}

	public IpAddress(String ipAddress) throws UnknownHostException {
		String PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
				+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
				+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
				+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
		Pattern pattern = Pattern.compile(PATTERN);
		Matcher matcher = pattern.matcher(ipAddress);

		if (!matcher.matches()) {
			throw new UnknownHostException(
					"IP Address: "
							+ ipAddress
							+ " is an invalid format!  Must be: [0-255].[0-255].[0-255].[0-255]");
		}
		this.ipAddress = ipAddress;
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ADDRESS")
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	// @OneToOne(cascade = CascadeType.PERSIST)
	// public Hotel getHotel() {
	// return hotel;
	// }
	//
	// public void setHotel(Hotel hotel) {
	// this.hotel = hotel;
	// }
}