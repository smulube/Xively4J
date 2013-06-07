// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * @author sam
 *
 */
public class ResponseError implements Serializable {

	private static final long serialVersionUID = -4364903901007662594L;

	private String title;
	@SerializedName("errors") private String message;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the errors
	 */
	public String getMessage() {
		return message;
	}
}
