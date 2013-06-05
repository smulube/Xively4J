// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.params.CoreConnectionPNames;

/**
 * @author sam
 *
 */
public class HttpClientBuilder {

	private static HttpClient httpClient;
	private static HttpRequestRetryHandler retryHandler;
	private static int retryCount;
	private static Object connectionTimeout;

	public static HttpClient getHttpClient() {
		if (httpClient == null) {
			httpClient = new DefaultHttpClient();
			if (retryHandler == null) {
				retryHandler = new DefaultHttpRequestRetryHandler(retryCount, false);
			}
			((AbstractHttpClient) httpClient).setHttpRequestRetryHandler(retryHandler);
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
		}

		return httpClient;
	}
}
