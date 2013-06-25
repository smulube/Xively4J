// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author sam
 *
 */
public class Channel extends DomainObjectImpl {

	private static final long serialVersionUID = -4713767723173762280L;

	private String at;
	private String current_value;
	private Double min_value;
	private Double max_value;
	private List<String> tags;
	private Unit unit;
	private List<Datapoint> datapoints;

	/**
	 * Default constructor
	 */
	public Channel() {
	}

	/**
	 * @param id
	 * @param current_value
	 * @param at
	 */
	public Channel(String id, String current_value, String at) {
		this.id = id;
		this.current_value = current_value;
		this.at = at;
	}

	@Override
	public boolean deepEquals(DomainObject obj) {
		if (!this.equals(obj)) {
			return false;
		}

		Channel other = (Channel) obj;

		return new EqualsBuilder().append(at, other.at)
				.append(current_value, other.current_value)
				.append(min_value, other.min_value)
				.append(max_value, other.max_value).append(tags, other.tags)
				.append(unit, other.unit).append(datapoints, other.datapoints)
				.isEquals();
	}

	/**
	 * @return the at
	 */
	public String getAt() {
		return at;
	}

	/**
	 * @return the current_value
	 */
	public String getCurrentValue() {
		return current_value;
	}

	/**
	 * @return the datapoints
	 */
	public List<Datapoint> getDatapoints() {
		return datapoints;
	}

	/**
	 * @return the max_value
	 */
	public Double getMaxValue() {
		return max_value;
	}

	/**
	 * @return the min_value
	 */
	public Double getMinValue() {
		return min_value;
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

	/**
	 * @param at
	 *            the at to set
	 */
	public void setAt(String at) {
		this.at = at;
	}

	/**
	 * @param current_value
	 *            the current_value to set
	 */
	public void setCurrentValue(String current_value) {
		this.current_value = current_value;
	}

	/**
	 * @param datapoints
	 *            the datapoints to set
	 */
	public void setDatapoints(List<Datapoint> datapoints) {
		this.datapoints = datapoints;
	}

	/**
	 * @param max_value
	 *            the max_value to set
	 */
	public void setMaxValue(Double max_value) {
		this.max_value = max_value;
	}

	/**
	 * @param max_value
	 *            the max_value to set as a String
	 */
	public void setMaxValue(String max_value) {
		if (max_value != null) {
			setMaxValue(Double.valueOf(max_value));
		}
	}

	/**
	 *
	 * @param min_value
	 *            the min_value to set
	 */
	public void setMinValue(Double min_value) {
		this.min_value = min_value;
	}

	/**
	 * @param min_value
	 *            the min_value to set
	 */
	public void setMinValue(String min_value) {
		if (min_value != null) {
			this.setMinValue(Double.valueOf(min_value));
		}
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("current_value", this.current_value).toString();
	}
}
