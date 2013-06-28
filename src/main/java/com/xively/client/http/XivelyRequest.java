// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.xively.client.XivelyConstants;

/**
 * Request class that contains the URI and parameters of the request as well as
 * expected type of response.
 *
 * The {@link generateUri} method should be used to build the full URI to which
 * this request should be made.
 *
 * @author sam
 *
 */
public class XivelyRequest {

    /**
     * URL encode value using UTF-8 charset.
     *
     * @param value
     * @return
     */
    private static String encode(String value) {
        try {
            return URLEncoder.encode(value, XivelyConstants.CHARSET_UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private String uri;

    private Map<String, String> params;

    private Type type;

    private Object object;

    /**
     * Add a single query parameter to this request.
     * 
     * @param key
     * @param value
     */
    public void addParam(String key, String value) {
        if (this.params == null) {
            this.params = new HashMap<String, String>();
        }

        this.params.put(key, value);
    }

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

        XivelyRequest other = (XivelyRequest) obj;

        return new EqualsBuilder().append(this.uri, other.uri)
                .append(this.type, other.type)
                .append(this.object, other.object).isEquals();
    }

    /**
     * Currently just returns the URI, but will do more when we have to handle
     * parameters.
     *
     * @return
     */
    public String generateUri() {
        if (this.uri == null) {
            return null;
        }

        StringBuilder uriBuilder = new StringBuilder(this.uri);

        if (this.params != null && !this.params.isEmpty()) {
            if (this.uri.indexOf('?') == -1) {
                uriBuilder.append('?');
            }

            buildParams(uriBuilder);
        }

        return uriBuilder.toString();
    }

    /**
     * @return the object
     */
    public Object getObject() {
        return this.object;
    }

    /**
     * @return the params
     */
    public Map<String, String> getParams() {
        return this.params;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return this.type;
    }

    /**
     *
     * @return
     */
    public String getUri() {
        return this.uri;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(57, 67).append(this.uri).append(this.type)
                .append(this.object).toHashCode();
    }

    /**
     * @param object
     */
    public XivelyRequest setObject(Object object) {
        this.object = object;
        return this;
    }

    /**
     * 
     * @param params
     *              the params to set
     * @return
     */
    public XivelyRequest setParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

    /**
     * @param type
     *            the type to set
     */
    public XivelyRequest setType(Type type) {
        this.type = type;
        return this;
    }

    /**
     * 
     * @param uri
     * @return
     */
    public XivelyRequest setUri(String uri) {
        this.uri = uri;
        return this;
    }

    /**
     * 
     * @param sb
     * @return
     */
    public XivelyRequest setUri(StringBuilder sb) {
        return setUri(sb.toString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("uri", this.uri)
                .append("type", this.type).append("object", this.object)
                .toString();
    }

    /**
     * @param paramEntry
     * @param uriBuilder
     */
    private void buildParam(Entry<String, String> paramEntry,
            StringBuilder uriBuilder) {
        uriBuilder.append(XivelyRequest.encode(paramEntry.getKey()));
        uriBuilder.append("=");
        uriBuilder.append(XivelyRequest.encode(paramEntry.getValue()));
    }

    /**
     * Build URL encoded query string out of our params Map.
     * 
     * @return
     */
    private void buildParams(StringBuilder uriBuilder) {
        Iterator<Entry<String, String>> iterator = this.params.entrySet()
                .iterator();

        while (iterator.hasNext()) {
            Entry<String, String> paramEntry = iterator.next();
            buildParam(paramEntry, uriBuilder);

            if (iterator.hasNext()) {
                uriBuilder.append("&");
            }
        }
    }
}
