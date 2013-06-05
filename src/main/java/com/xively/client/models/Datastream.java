// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import java.util.Collection;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.xively.client.utils.JsonUtils;

/**
 * @author sam
 *
 */
public class Datastream extends DomainObjectImpl {

	private static final long serialVersionUID = -4713767723173762280L;

	private String id;
	private String at;
	private String current_value;
	private double min_value;
	private double max_value;
	private Collection<String> tags;
	private Unit unit;
	private Collection<Datapoint> datapoints;

	@Override
	public Object getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see com.xively.client.models.DomainObject#setId(java.lang.Object)
	 */
	@Override
	public void setId(Object id) {
		this.id = (String) id;
	}

	/**
	 * @return the at
	 */
	public String getAt() {
		return at;
	}

	/**
	 * @param at the at to set
	 */
	public void setAt(String at) {
		this.at = at;
	}

	/**
	 * @return the current_value
	 */
	public String getCurrentValue() {
		return current_value;
	}

	/**
	 * @param current_value the current_value to set
	 */
	public void setCurrentValue(String current_value) {
		this.current_value = current_value;
	}

	/**
	 * @return the min_value
	 */
	public double getMinValue() {
		return min_value;
	}

	/**
	 * @param min_value the min_value to set
	 */
	public void setMinValue(String min_value) {
		if (min_value != null) {
			this.setMinValue(Double.valueOf(min_value).doubleValue());
		}
	}

	/**
	 *
	 * @param min_value the min_value to set
	 */
	public void setMinValue(double min_value) {
		this.min_value = min_value;
	}

	/**
	 * @return the max_value
	 */
	public double getMaxValue() {
		return max_value;
	}

	/**
	 * @param max_value the max_value to set as a String
	 */
	public void setMaxValue(String max_value) {
		if (max_value != null) {
			setMaxValue(Double.valueOf(max_value).doubleValue());
		}
	}

	/**
	 * @param max_value the max_value to set
	 */
	public void setMaxValue(double max_value) {
		this.max_value = max_value;
	}

	/**
	 * @return the tags
	 */
	public Collection<String> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(Collection<String> tags) {
		this.tags = tags;
	}

	/**
	 * @return the unit
	 */
	public Unit getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	/**
	 * @return the datapoints
	 */
	public Collection<Datapoint> getDatapoints() {
		return datapoints;
	}

	/**
	 * @param datapoints the datapoints to set
	 */
	public void setDatapoints(Collection<Datapoint> datapoints) {
		this.datapoints = datapoints;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean deepEquals(DomainObject obj) {
		if (!this.equals(obj)) {
			return false;
		}

		Datastream other = (Datastream) obj;

		return new EqualsBuilder()
				.append(at, other.at)
				.append(current_value, other.current_value)
				.append(min_value, other.min_value)
				.append(max_value, other.max_value)
				.append(tags, other.tags)
				.append(unit, other.unit)
				.append(datapoints, other.datapoints)
				.isEquals();
	}

	/* (non-Javadoc)
	 * @see com.xively.client.models.Jsonable#toJson()
	 */
	@Override
	public String toJson() {
		return JsonUtils.toJson(this);
	}
}
