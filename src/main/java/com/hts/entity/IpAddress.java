package com.hts.entity;

import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "IPADDRESS")
public class IpAddress {

	private Integer id;
	private String ipAddress;
	private Room room;

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

	@Column(name = "ADDRESS", unique = true)
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}