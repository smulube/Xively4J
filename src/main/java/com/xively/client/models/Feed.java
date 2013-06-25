// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import java.net.URI;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.SerializedName;

/**
 * @author sam
 *
 */
public class Feed extends DomainObjectImpl {

	private static final long serialVersionUID = -6276027532856247302L;

	private String title;
	private String description;
	private String updatedAt;
	private String createdAt;
	private URI website;
	private List<String> tags;
	private Location location;
	@SerializedName("private")
	private boolean isPrivate;
	@SerializedName("datastreams")
	private List<Channel> channels;

	/**
	 * @param channel
	 */
	public void addChannel(Channel channel) {
		this.channels.add(channel);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.xively.client.models.DomainObject#deepEquals(com.xively.client.models
	 * .DomainObject)
	 */
	@Override
	public boolean deepEquals(DomainObject obj) {
		if (!this.equals(obj)) {
			return false;
		}

		Feed other = (Feed) obj;

		return new EqualsBuilder().append(title, other.title)
				.append(description, other.description)
				.append(updatedAt, other.updatedAt)
				.append(createdAt, other.createdAt)
				.append(website, other.website).append(tags, other.tags)
				.append(location, other.location)
				.append(isPrivate, other.isPrivate).isEquals();
	}

	/**
	 * @return the createdAt
	 */
	public String getCreatedAt() {
		return createdAt;
	}

	/**
	 * @return the channels
	 */
	public List<Channel> getChannels() {
		return channels;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @return the tags
	 */
	public List<String> getTags() {
		return tags;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the updatedAt
	 */
	public String getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @return the website
	 */
	public URI getWebsite() {
		return website;
	}

	/**
	 * @return the isPrivate
	 */
	public boolean isPrivate() {
		return isPrivate;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @param channels
	 *            the channels to set
	 */
	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @param isPrivate
	 *            the isPrivate to set
	 */
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @param website
	 *            the website to set
	 */
	public void setWebsite(URI website) {
		this.website = website;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("title", this.title)
				.append("description", this.description)
				.append("updatedAt", this.updatedAt)
				.append("createdAt", this.createdAt)
				.append("website", this.website)
				.append("location", this.location)
				.append("private", this.isPrivate).append("tags", this.tags)
				.toString();
	}
}
