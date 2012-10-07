package com.hts.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CHANNEL")
public class Channel {

	public Channel(String channelName, String broadcastStreamName) {
		this.channelName = channelName;
		this.broadcastStreamName = broadcastStreamName;
	}

	public Channel() {
	}

	@Id
	@GeneratedValue
	@Column(name = "CHANNEL_ID", nullable = false)
	private Integer id;

	@Column(name = "CHANNEL_NAME", unique = true, nullable = false)
	private String channelName;

	@Column(name = "DESCRIPTION")
	private String Description;

	@Column(name = "BROADCASTSTREAM_NAME", unique = true, nullable = false)
	private String broadcastStreamName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	String getBroadcastStream() {
		return this.broadcastStreamName;
	}

	void setBroadcastStream(String broadcastStreamName) {
		this.broadcastStreamName = broadcastStreamName;
	}

	@Override
	public String toString() {
		return "Channel [id=" + id + ", channelName=" + channelName
				+ ", Description=" + Description + ", broadcastStreamName="
				+ broadcastStreamName + "]";
	}

}
