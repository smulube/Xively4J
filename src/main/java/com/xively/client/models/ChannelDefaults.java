// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author sam
 *
 */
public class ChannelDefaults implements Serializable {

	private static final long serialVersionUID = 6980990035331640539L;

	private String id;
	private List<String> tags;
	private Unit unit;

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

		ChannelDefaults other = (ChannelDefaults) obj;

		return new EqualsBuilder().append(id, other.id)
				.append(tags, other.tags).append(unit, other.unit).isEquals();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the tags
	 */
	public List<String> getTags() {
		return tags;
	}

	/**
	 * @return the unit
	 */
	public Unit getUnit() {
		return unit;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(167, 149).append(id).append(tags)
				.append(unit).toHashCode();
	}

	/**
	 * @param id
	 *            the id to set
	 * @return
	 */
	public ChannelDefaults setId(String id) {
		this.id = id;

		return this;
	}

	/**
	 * @param tags
	 *            the tags to set
	 * @return
	 */
	public ChannelDefaults setTags(List<String> tags) {
		this.tags = tags;

		return this;
	}

	/**
	 * @param unit
	 *            the unit to set
	 * @return
	 */
	public ChannelDefaults setUnit(Unit unit) {
		this.unit = unit;

		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("tags", this.tags).append("unit", this.unit).toString();
	}
}
