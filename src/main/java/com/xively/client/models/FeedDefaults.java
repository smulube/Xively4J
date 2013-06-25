// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.SerializedName;

/**
 * @author sam
 *
 */
public class FeedDefaults implements Serializable {

	private static final long serialVersionUID = 4702105519710563189L;

	private String title;
	private String description;
	private URI website;
	private List<String> tags;
	@SerializedName("private")
	private boolean isPrivate;
	@SerializedName("datastreams")
	private List<ChannelDefaults> channels;

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		FeedDefaults other = (FeedDefaults) obj;

		return new EqualsBuilder()
			.append(title, other.title)
			.append(description, other.description)
			.append(website, other.website)
			.append(tags, other.tags)
			.append(isPrivate, other.isPrivate)
			.append(channels, other.channels).isEquals();
	}

	/**
	 * @return the channels
	 */
	public List<ChannelDefaults> getChannels() {
		return channels;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
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
	 * @return the website
	 */
	public URI getWebsite() {
		return website;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(67, 109)
			.append(title)
			.append(description)
			.append(website)
			.append(tags)
			.append(isPrivate)
			.toHashCode();
	}

	/**
	 * @return the isPrivate
	 */
	public boolean isPrivate() {
		return isPrivate;
	}

	/**
	 * @param channels
	 *            the channels to set
	 * @return
	 */
	public FeedDefaults setChannels(List<ChannelDefaults> channels) {
		this.channels = channels;

		return this;
	}

	/**
	 * @param description
	 *            the description to set
	 * @return
	 */
	public FeedDefaults setDescription(String description) {
		this.description = description;

		return this;
	}

	/**
	 * @param isPrivate
	 *            the isPrivate to set
	 * @return
	 */
	public FeedDefaults setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;

		return this;
	}

	/**
	 * @param tags
	 *            the tags to set
	 * @return
	 */
	public FeedDefaults setTags(List<String> tags) {
		this.tags = tags;

		return this;
	}

	/**
	 * @param title
	 *            the title to set
	 * @return
	 */
	public FeedDefaults setTitle(String title) {
		this.title = title;

		return this;
	}

	/**
	 * @param website
	 *            the website to set
	 * @return
	 */
	public FeedDefaults setWebsite(URI website) {
		this.website = website;

		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("title", this.title)
			.append("description", this.description)
			.append("website", this.website)
			.append("tags", this.tags)
			.append("private", this.isPrivate).toString();
	}

}
