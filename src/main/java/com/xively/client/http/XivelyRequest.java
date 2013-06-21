// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http;

import java.lang.reflect.Type;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

	private String uri;

	private Type type;

	private Object object;

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

	/**
	 *
	 * @return
	 */
	public String getUri() {
		return this.uri;
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
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param object
	 */
	public XivelyRequest setObject(Object object) {
		this.object = object;
		return this;
	}

	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * Currently just returns the URI, but will do more when we have to handle
	 * parameters.
	 *
	 * @return
	 */
	public String generateUri() {
		return getUri();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("uri", uri)
				.append("type", type).append("object", object).toString();
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

		return new EqualsBuilder().append(this.uri, other.uri).isEquals();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(57, 67).append(this.uri).toHashCode();
	}
}
