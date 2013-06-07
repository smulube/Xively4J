// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import java.io.Serializable;

/**
 * @author sam
 *
 */
public interface DomainObject extends Serializable {

	/**
	 * Return the id attribute of this domain object.
	 *
	 * @return
	 */
	public Object getId();

	/**
	 * The id attribute to set.
	 *
	 * @param id
	 */
	public void setId(Object id);

	/**
	 * Deep equality test across all attributes.
	 *
	 * @param obj
	 * @return
	 */
	public boolean deepEquals(DomainObject obj);

}
