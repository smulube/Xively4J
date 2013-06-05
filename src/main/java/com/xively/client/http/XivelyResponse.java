// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http;

import org.apache.http.HttpResponse;



/**
 * Response class that provides access to the parsed response body, status code, and any
 * headers returned from the Xively API. Does not do any JSON parsing.
 *
 * @author sam
 *
 */
public class XivelyResponse {

	private final HttpResponse response;
	private final Object domainObject;

	public XivelyResponse(HttpResponse response, Object domainObject) {
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
		return response.getFirstHeader(name).getValue();
	}
}