package com.hts.entities;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BroadcastStreamXML {

	public BroadcastStreamXML(String streamName) {
		this.streamName = streamName;
	}

	public BroadcastStreamXML() {
	}

	private Integer id;

	private String streamName;

	private Boolean isActive;

	private Date publishedDate;

	private Date unpublishedDate;

	private Date updateDate;

	public String getStreamName() {
		return streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public Date getUnpublishedDate() {
		return unpublishedDate;
	}

	public void setUnpublishedDate(Date unpublishedDate) {
		this.unpublishedDate = unpublishedDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isActive() {
		return isActive == true;
	}

	public void setIsActive(Boolean b) {
		isActive = b;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return streamName;
	}

	public void setName(String name) {
		this.streamName = name;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

}
