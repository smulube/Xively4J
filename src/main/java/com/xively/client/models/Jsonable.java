// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

/**
 * @author sam
 *
 */
public interface Jsonable {

	/**
	 * Return JSON representation of the object.
	 *
	 * @return
	 */
	public String toJson();
}
