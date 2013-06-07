// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http;

import java.net.HttpURLConnection;



/**
 * Response class that provides access to the parsed response body, status code, and any
 * headers returned from the Xively API. Does not do any JSON parsing.
 *
 * @author sam
 *
 */
public class XivelyResponse {

	protected final HttpURLConnection response;
	protected final Object domainObject;

	public XivelyResponse(HttpURLConnection response, Object domainObject) {
		this.response = response;
		this.domainObject = domainObject;
	}

	/**
	 * @return
	 */
	public Object getDomainObject() {
		return this.domainObject;
	}

	/**
	 *
	 * @param name
	 * @return
	 */
	public String getHeader(String name) {
		return response.getHeaderField(name);
	}
}
