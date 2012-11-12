package com.hts.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "BROADCASTSTREAM")
public class BroadcastStream {

	public BroadcastStream(String streamName) {
		this.streamName = streamName;
	}

	public BroadcastStream() {
	}

	public final static boolean ACTIVE = true;
	public final static boolean INACTIVE = false;

	@Id
	@GeneratedValue
	@Column(name = "STREAM_ID", nullable = false)
	private Integer id;

	@Column(name = "STREAM_NAME", nullable = false)
	private String streamName;

	@Column(name = "ACTIVE")
	private Boolean active;

	@Column(name = "PUBLISHED_DATE")
	private Date publishedDate;

	@Column(name = "UNPUBLISHED_DATE")
	private Date unpublishedDate;

	@Column(name = "UPDATE_DATE")
	private Date updateDate;

	public String getStreamName() {
		return streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean status) {
		this.active = status;
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

//	public boolean isActive() {
//		return isActive == true;
//	}

//	public boolean isInactive() {
//		return isActive == false;
//	}

	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

		return "[BroadcastStream:id:" + id + " name: " + streamName.toString()
				+ " publishedDate:" + format.format(publishedDate)
				+ " updateDate:" + format.format(updateDate) + " isActive: "
				+ isActive() + "]";
	}

	// DateFormat myformat = new SimpleDateFormat("yyyy.MM.dd");
	// System.out.println(myformat.format(new Date()));
	// try { // DateFormat can parse dates too
	// Date leapday = myformat.parse("2000.02.29");
	// }
	// catch (ParseException e) { /* Handle parsing exception */ }

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
