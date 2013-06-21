// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xively.client.XivelyClient;

/**
 * Response class that provides access to the parsed response body, status code,
 * and any headers returned from the Xively API. Does not do any JSON parsing.
 *
 * @author sam
 *
 */
public class XivelyResponse {

	private final Logger logger = LoggerFactory.getLogger(XivelyResponse.class);

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

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("response", response)
				.append("domainObject", domainObject).toString();
	}

	/**
	 * @return
	 */
	public String getIdFromLocation() {
		String location = getHeader(XivelyClient.HEADER_LOCATION);

		if (location != null) {
			URL url;
			try {
				url = new URL(location);
			} catch (MalformedURLException e) {
				logger.error("Error parsing location header", e);
				return null;
			}
			String[] tokens = url.getPath().split("/");
			return tokens[tokens.length - 1];
		} else {
			return null;
		}
	}
}
