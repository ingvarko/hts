package com.hts.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "SUBSCRIPTIONPACKAGE")
public class SubscriptionPackage {

	public SubscriptionPackage() {
		super();
	}

	public SubscriptionPackage(String subscriptionPackageName) {
		super();
		this.subscriptionPackageName = subscriptionPackageName;
	}

	private Integer id;
	private String subscriptionPackageName;
	private String description;
	private String cost;

	@Id
	@GeneratedValue
	@Column(name = "SUBSCRIPTIONPACKAGE_ID", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "SUBSCRIPTIONPACKAGE_NAME", nullable = false)
	public String getSubscriptionPackageName() {
		return this.subscriptionPackageName;
	}

	public void setSubscriptionPackageName(String subscriptionPackageName) {
		this.subscriptionPackageName = subscriptionPackageName;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private List<Channel> channels;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "SUBSCRIPTION_CHANNEL", joinColumns = { @JoinColumn(name = "SUBSCRIPTIONPACKAGE_ID") }, inverseJoinColumns = { @JoinColumn(name = "CHANNEL_ID") })
	public List<Channel> getChannels() {
		return channels;
	}
	
	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}
	
	@Column(name = "COST")
	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "SubscriptionPackage [id=" + id + ", subscriptionPackageName="
				+ subscriptionPackageName + ", description=" + description
				+ ", Cost=" + cost + ", channels=" + channels + "]";
	}
}
