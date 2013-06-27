// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.xively.client.utils.JsonUtils;

/**
 * @author sam
 * 
 */
public class Product extends DomainObjectImpl {

    private static final long serialVersionUID = 8483131778172380123L;

    private String name;
    private String description;
    @SerializedName("product_id")
    private transient String id;
    private transient String secret;
    private transient String state;
    @SerializedName("devices_count")
    private transient Integer devicesCount;
    @SerializedName("activated_devices_count")
    private transient Integer activatedDevicesCount;
    @SerializedName("feed_defaults")
    private FeedDefaults feedDefaults;
    private String user;

    private transient Logger logger = LoggerFactory.getLogger(Product.class);

    /**
     * Default constructor
     */
    public Product() {
    }

    /**
     * @param productJson
     */
    public Product(JsonObject productJson) {
        this.name = productJson.get("name").getAsString();
        this.description = productJson.get("description").getAsString();
        this.id = getAsString(productJson.get("product_id"));
        this.secret = getAsString(productJson.get("secret"));
        this.state = getAsString(productJson.get("state"));
        this.devicesCount = getAsInt(productJson.get("devices_count"));
        this.activatedDevicesCount = getAsInt(productJson
                .get("activated_devices_count"));

        if (productJson.get("feed_defaults") != null) {
            this.feedDefaults = JsonUtils.fromJson(
                    productJson.get("feed_defaults").toString(),
                    FeedDefaults.class);
        }

        this.user = getAsString(productJson.get("user"));
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

        Product other = (Product) obj;

        return new EqualsBuilder()
        .append(this.name, other.name)
        .append(this.description, other.description)
        .append(this.secret, other.secret)
        .append(this.state, other.state)
        .append(this.devicesCount, other.devicesCount)
        .append(this.activatedDevicesCount, other.activatedDevicesCount)
        .append(this.feedDefaults, other.feedDefaults).isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.xively.client.models.DomainObjectImpl#equals(java.lang.Object)
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

        DomainObject other = (DomainObject) obj;

        return new EqualsBuilder().append(getId(), other.getId()).isEquals();
    }

    /**
     * @return the activated_devices_count
     */
    public Integer getActivatedDevicesCount() {
        return this.activatedDevicesCount;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return the devicesCount
     */
    public Integer getDevicesCount() {
        return this.devicesCount;
    }

    /**
     * @return the feedDefaults
     */
    public FeedDefaults getFeedDefaults() {
        return this.feedDefaults;
    }

    /**
     * @return the id
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the secret
     */
    public String getSecret() {
        return this.secret;
    }

    /**
     * @return the state
     */
    public String getState() {
        return this.state;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return this.user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.xively.client.models.DomainObjectImpl#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(89, 179).append(getId()).toHashCode();
    }

    /**
     * @param description
     *            the description to set
     * @return
     */
    public Product setDescription(String description) {
        this.description = description;

        return this;
    }

    /**
     * @param feedDefaults
     *            the feedDefaults to set
     * @return
     */
    public Product setFeedDefaults(FeedDefaults feedDefaults) {
        this.feedDefaults = feedDefaults;

        return this;
    }

    /**
     * @param id
     *            the id to set
     * @return
     */
    @Override
    public Product setId(String id) {
        this.id = id;

        return this;
    }

    /**
     * @param name
     *            the name to set
     * @return
     */
    public Product setName(String name) {
        this.name = name;

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", this.name)
                .append("description", this.description).append("id", this.id)
                .append("secret", this.secret).append("state", this.state)
                .append("devicesCount", this.devicesCount)
                .append("activatedDevicesCount", this.activatedDevicesCount)
                .append("feedDefaults", this.feedDefaults).toString();
    }

    /**
     * @param jsonElement
     * @return
     */
    private Integer getAsInt(JsonElement jsonElement) {
        if (jsonElement != null) {
            return jsonElement.getAsInt();
        } else {
            return null;
        }
    }

    /**
     * @param jsonElement
     * @return
     */
    private String getAsString(JsonElement jsonElement) {
        if (jsonElement != null) {
            return jsonElement.getAsString();
        } else {
            return null;
        }
    }

}
