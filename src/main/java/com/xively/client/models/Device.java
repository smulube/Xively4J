// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import java.util.Calendar;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author sam
 *
 */
public class Device extends DomainObjectImpl {

    private static final long serialVersionUID = 5634986534932545202L;

    private String serial;
    private String activationCode;
    private Calendar createdAt;
    private Calendar activatedAt;
    private String feedId;
    private String apiKey;

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

        Device other = (Device) obj;

        return new EqualsBuilder().append(this.activationCode, other.activationCode)
                .append(this.createdAt, other.createdAt)
                .append(this.activatedAt, other.activatedAt)
                .append(this.feedId, other.feedId).append(this.apiKey, other.apiKey)
                .isEquals();
    }

    /**
     * @return the activatedAt
     */
    public Calendar getActivatedAt() {
        return this.activatedAt;
    }

    /**
     * @return the activationCode
     */
    public String getActivationCode() {
        return this.activationCode;
    }

    /**
     * @return the apiKey
     */
    public String getApiKey() {
        return this.apiKey;
    }

    /**
     * @return the createdAt
     */
    public Calendar getCreatedAt() {
        return this.createdAt;
    }

    /**
     * @return the feedId
     */
    public String getFeedId() {
        return this.feedId;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.xively.client.models.DomainObjectImpl#getId()
     */
    @Override
    public String getId() {
        return this.serial;
    }

    /**
     * @return the serial
     */
    public String getSerial() {
        return this.serial;
    }

    /**
     * @param activatedAt
     *            the activatedAt to set
     */
    public void setActivatedAt(Calendar activatedAt) {
        this.activatedAt = activatedAt;
    }

    /**
     * @param activationCode
     *            the activationCode to set
     */
    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    /**
     * @param apiKey
     *            the apiKey to set
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * @param createdAt
     *            the createdAt to set
     */
    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @param feedId
     *            the feedId to set
     */
    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.xively.client.models.DomainObjectImpl#setId(java.lang.String)
     */
    @Override
    public DomainObject setId(String id) {
        this.serial = id;
        return this;
    }

    /**
     * @param serial
     *            the serial to set
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("serial", this.serial)
                .append("activationCode", this.activationCode)
                .append("createdAt", this.createdAt)
                .append("activatedAt", this.activatedAt)
                .append("feedId", this.feedId).append("apiKey", this.apiKey)
                .toString();
    }

}
