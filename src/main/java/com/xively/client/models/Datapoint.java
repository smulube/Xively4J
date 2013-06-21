// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author sam
 *
 */
public class Datapoint extends DomainObjectImpl {

	/**
	 *
	 */
	private static final long serialVersionUID = -4110823408157711203L;

	private String value;

	private String at;

	/**
	 * Default constructor
	 */
	public Datapoint() {
	}

	/**
	 * Convenience constructor.
	 *
	 * @param value
	 * @param at
	 */
	public Datapoint(String value, String at) {
		this.value = value;
		this.at = at;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the at
	 */
	public String getAt() {
		return at;
	}

	/**
	 * @param at
	 *            the at to set
	 */
	public void setAt(String at) {
		this.at = at;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("at", at)
				.append("value", value).toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.xively.client.models.DomainObject#getId()
	 */
	@Override
	public String getId() {
		return this.at;
	}

	/* (non-Javadoc)
	 * @see com.xively.client.models.DomainObject#setId(java.lang.Object)
	 */
	@Override
	public void setId(String id) {
		this.at = (String) id;
	}

	/*
	 * (non-Javadoc)
	 * @see com.xively.client.models.DomainObject#deepEquals(com.xively.client.models.DomainObject)
	 */
	@Override
	public boolean deepEquals(DomainObject obj) {
		if (!this.equals(obj)) {
			return false;
		}

		Datapoint other = (Datapoint) obj;

		return new EqualsBuilder().append(value, other.value).isEquals();
	}
}
