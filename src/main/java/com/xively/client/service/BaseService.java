// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.service;

import com.xively.client.XivelyClient;
import com.xively.client.models.DomainObject;

/**
 * @author sam
 *
 */
public abstract class BaseService {
	protected final XivelyClient client;

	/**
	 * Default constructor.
	 */
	public BaseService() {
		this(new XivelyClient());
	}

	/**
	 * Constructor with explicit client instance.
	 *
	 * @param client
	 */
	public BaseService(XivelyClient client) {
		if (client == null) {
			throw new IllegalArgumentException("XivelyClient cannot be null");
		}
		this.client = client;
	}

	/**
	 * Return the client instance.
	 *
	 * @return
	 */
	public XivelyClient getClient() {
		return this.client;
	}

	/**
	 * Throw {@link IllegalArgumentException} if id is null or empty.
	 * @param id
	 */
	protected void checkDomainObjectId(String id, String label) {
		if (id == null) {
			throw new IllegalArgumentException(label + " ID cannot be null");
		}

		if (id.length() == 0) {
			throw new IllegalArgumentException(label + " ID cannot be empty");
		}
	}

	/**
	 *
	 * @param object
	 * @param label
	 */
	protected void checkDomainObject(DomainObject object, String label) {
		if (object == null) {
			throw new IllegalArgumentException(label + " cannot be null");
		}
	}
}
