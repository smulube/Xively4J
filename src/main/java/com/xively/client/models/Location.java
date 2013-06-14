// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author sam
 *
 */
public class Location implements Serializable {

	public enum Exposure {
		INDOOR("indoor"), OUTDOOR("outdoor");

		private final String value;

		private Exposure(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public static Exposure fromString(String value) {
			return Exposure.valueOf(value.toUpperCase());
		}
	}

	public enum Disposition {
		FIXED("fixed"), MOBILE("mobile");

		private final String value;

		private Disposition(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public static Disposition fromString(String value) {
			return Disposition.valueOf(value.toUpperCase());
		}
	}

	public enum Domain {
		PHYSICAL("physical"), VIRTUAL("virtual");

		private final String value;

		private Domain(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		/**
		 * Case insensitive version of valueOf.
		 *
		 * @param value
		 * @return
		 */
		public static Domain fromString(String value) {
			return Domain.valueOf(value.toUpperCase());
		}
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -2595713811168739735L;

	private String name;
	private double latitude;
	private double longitude;
	private String elevation;
	private Exposure exposure;
	private Disposition disposition;
	private Domain domain;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the elevation
	 */
	public String getElevation() {
		return elevation;
	}

	/**
	 * @param elevation
	 *            the elevation to set
	 */
	public void setElevation(String elevation) {
		this.elevation = elevation;
	}

	/**
	 * @return the exposure
	 */
	public Exposure getExposure() {
		return exposure;
	}

	/**
	 * @param exposure
	 *            the exposure to set
	 */
	public void setExposure(Exposure exposure) {
		this.exposure = exposure;
	}

	/**
	 * @return the disposition
	 */
	public Disposition getDisposition() {
		return disposition;
	}

	/**
	 * @param disposition
	 *            the disposition to set
	 */
	public void setDisposition(Disposition disposition) {
		this.disposition = disposition;
	}

	/**
	 * @return the domain
	 */
	public Domain getDomain() {
		return domain;
	}

	/**
	 * @param domain
	 *            the domain to set
	 */
	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(23, 91).append(name).append(latitude)
				.append(longitude).append(elevation).append(exposure)
				.append(disposition).append(domain).toHashCode();
	}

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

		Location other = (Location) obj;

		return new EqualsBuilder().append(name, other.name)
				.append(latitude, other.latitude)
				.append(longitude, other.longitude)
				.append(elevation, other.elevation)
				.append(exposure, other.exposure)
				.append(disposition, other.disposition)
				.append(domain, other.domain).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", name)
				.append("latitude", latitude).append("longitude", longitude)
				.append("elevation", elevation).append("exposure", exposure)
				.append("disposition", disposition).append("domain", domain)
				.toString();
	}

}
