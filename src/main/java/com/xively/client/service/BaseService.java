// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.service;

import com.xively.client.XivelyClient;
import com.xively.client.http.XivelyRequest;

/**
 * @author sam
 *
 */
public abstract class BaseService {
	protected final XivelyClient client;

	public BaseService() {
		this(new XivelyClient());
	}

	public BaseService(XivelyClient client) {
		if (client == null) {
			throw new IllegalArgumentException("XivelyClient cannot be null");
		}
		this.client = client;
	}

	protected XivelyRequest createRequest() {
		return new XivelyRequest();
	}

}
