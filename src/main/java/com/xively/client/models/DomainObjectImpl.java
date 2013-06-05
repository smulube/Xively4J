// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author sam
 *
 */
public abstract class DomainObjectImpl implements DomainObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1579033107880707099L;

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(getId()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (this == obj) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}

		DomainObject other = (DomainObject) obj;

		return new EqualsBuilder().
				append(getId(), other.getId())
				.isEquals();
	}
}
