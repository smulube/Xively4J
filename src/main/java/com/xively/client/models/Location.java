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

    public static enum Disposition {
        FIXED("fixed"), MOBILE("mobile");

        public static Disposition fromString(String value) {
            return Disposition.valueOf(value.toUpperCase());
        }

        private final String value;

        private Disposition(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static enum Domain {
        PHYSICAL("physical"), VIRTUAL("virtual");

        /**
         * Case insensitive version of valueOf.
         *
         * @param value
         * @return
         */
        public static Domain fromString(String value) {
            return Domain.valueOf(value.toUpperCase());
        }

        private final String value;

        private Domain(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static enum Exposure {
        INDOOR("indoor"), OUTDOOR("outdoor");

        public static Exposure fromString(String value) {
            return Exposure.valueOf(value.toUpperCase());
        }

        private final String value;

        private Exposure(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
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

        return new EqualsBuilder().append(this.name, other.name)
                .append(this.latitude, other.latitude)
                .append(this.longitude, other.longitude)
                .append(this.elevation, other.elevation)
                .append(this.exposure, other.exposure)
                .append(this.disposition, other.disposition)
                .append(this.domain, other.domain).isEquals();
    }

    /**
     * @return the disposition
     */
    public Disposition getDisposition() {
        return this.disposition;
    }

    /**
     * @return the domain
     */
    public Domain getDomain() {
        return this.domain;
    }

    /**
     * @return the elevation
     */
    public String getElevation() {
        return this.elevation;
    }

    /**
     * @return the exposure
     */
    public Exposure getExposure() {
        return this.exposure;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(23, 91)
        .append(this.name)
        .append(this.latitude)
        .append(this.longitude)
        .append(this.elevation)
        .append(this.exposure)
        .append(this.disposition)
        .append(this.domain).toHashCode();
    }

    /**
     * @param disposition
     *            the disposition to set
     */
    public void setDisposition(Disposition disposition) {
        this.disposition = disposition;
    }

    /**
     * @param domain
     *            the domain to set
     */
    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    /**
     * @param elevation
     *            the elevation to set
     */
    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    /**
     * @param exposure
     *            the exposure to set
     */
    public void setExposure(Exposure exposure) {
        this.exposure = exposure;
    }

    /**
     * @param latitude
     *            the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @param longitude
     *            the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", this.name)
                .append("latitude", this.latitude).append("longitude", this.longitude)
                .append("elevation", this.elevation).append("exposure", this.exposure)
                .append("disposition", this.disposition).append("domain", this.domain)
                .toString();
    }

}
