// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.exceptions;

import java.io.IOException;

import com.xively.client.models.ResponseError;

/**
 * @author sam
 *
 */
public class RequestException extends IOException {

	private static final long serialVersionUID = -4288987091458481848L;
	private final ResponseError responseError;
	private final int statusCode;

	public RequestException(ResponseError error, int statusCode) {
		super();
		this.responseError = error;
		this.statusCode = statusCode;
	}

	/**
	 * @return the responseError
	 */
	public ResponseError getResponseError() {
		return responseError;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return responseError != null ? responseError.getMessage() : super.getMessage();
	}
}
