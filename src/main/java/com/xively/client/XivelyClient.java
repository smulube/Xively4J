// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonParseException;
import com.xively.client.exceptions.RequestException;
import com.xively.client.http.XivelyRequest;
import com.xively.client.http.XivelyResponse;
import com.xively.client.models.DomainObject;
import com.xively.client.models.ResponseError;
import com.xively.client.service.FeedService;
import com.xively.client.utils.JsonUtils;

/**
 * @author sam
 *
 */
public class XivelyClient {
	public static final String VERSION = "0.1.0-SNAPSHOT";

	protected static final String API_VERSION = "v2";

	protected static final String HEADER_API_KEY = "X-ApiKey";

	protected static final String HEADER_USER_AGENT = "User-Agent";

	protected static final String HEADER_ACCEPT = "Accept";

	protected static final String HEADER_LOCATION = "Location";

	protected static final String HTTP_GET = "GET";

	protected static final String HTTP_POST = "POST";

	protected static final String HTTP_PUT = "PUT";

	protected static final String HTTP_DELETE = "DELETE";

	protected static final String USER_AGENT = "Xively4J/" + VERSION;

	protected static final int HTTP_OK = 200;
	protected static final int HTTP_CREATED = 201;
	protected static final int HTTP_BAD_REQUEST = 400;
	protected static final int HTTP_UNAUTHORIZED = 401;
	protected static final int HTTP_FORBIDDEN = 403;
	protected static final int HTTP_NOT_FOUND = 404;
	protected static final int HTTP_UNPROCESSABLE_ENTITY = 422;
	protected static final int HTTP_INTERNAL_SERVER_ERROR = 500;
	protected static final int HTTP_SERVICE_UNAVAILABLE = 503;

	private static final Integer DEFAULT_CONNECTION_TIMEOUT = 3000;
	private static final Integer DEFAULT_SOCKET_TIMEOUT = 3000;

	protected final String baseUri;

	private String apiKey;
	private String userAgent = USER_AGENT;
	private final Integer connectionTimeout;
	private final Integer socketTimeout;

	private HttpClient httpClient;
	private FeedService feedService;

	private final int bufferSize = 8192;

	private final Logger logger = LoggerFactory.getLogger(XivelyClient.class);

	public XivelyClient() {
		this(Constants.API_HOST);
	}

	/**
	 * @param hostname
	 */
	public XivelyClient(String hostname) {
		this(hostname, -1, Constants.PROTOCOL_HTTPS);
	}

	/**
	 *
	 * @param hostname
	 * @param port
	 */
	public XivelyClient(String hostname, int port) {
		this(hostname, port, Constants.PROTOCOL_HTTPS);
	}

	/**
	 * @param hostname
	 * @param port
	 * @param scheme
	 */
	public XivelyClient(String hostname, int port, String scheme) {
		this(hostname, port, scheme, DEFAULT_SOCKET_TIMEOUT,
				DEFAULT_CONNECTION_TIMEOUT);
	}

	/**
	 *
	 * @param hostname
	 * @param port
	 * @param scheme
	 * @param socketTimeout
	 * @param connectionTimeout
	 */
	public XivelyClient(String hostname, int port, String scheme,
			Integer socketTimeout, Integer connectionTimeout) {
		StringBuilder uri = new StringBuilder(scheme);
		uri.append("://");
		uri.append(hostname);
		if (port > 0) {
			uri.append(":").append(port);
		}
		uri.append("/").append(API_VERSION);

		this.baseUri = uri.toString();
		this.socketTimeout = socketTimeout;
		this.connectionTimeout = connectionTimeout;
		logger.info(this.baseUri);

		this.httpClient = buildHttpClient();
	}

	/**
	 * @param apiKey
	 *            the apiKey to set
	 * @return
	 */
	public XivelyClient setApiKey(String apiKey) {
		this.apiKey = apiKey;

		return this;
	}

	/**
	 * @param userAgent
	 *            the userAgent to set
	 * @return
	 */
	public XivelyClient setUserAgent(final String userAgent) {
		if (userAgent != null && userAgent.length() > 0) {
			this.userAgent = userAgent;
		} else {
			this.userAgent = USER_AGENT;
		}

		return this;
	}

	/**
	 * Set the connectionTimeout for the underlying HttpClient instance.
	 *
	 * @param connectionTimeout
	 * @return
	 */
	/*
	 * public XivelyClient setConnectionTimeout(Integer connectionTimeout) { if
	 * (connectionTimeout != null) { this.connectionTimeout = connectionTimeout;
	 * } else { this.connectionTimeout = DEFAULT_CONNECTION_TIMEOUT; }
	 *
	 * return this; }
	 *//**
	 * Set the socket timeout
	 *
	 * @param socketTimeout
	 * @return
	 */
	/*
	 * public XivelyClient setSocketTimeout(Integer socketTimeout) { if
	 * (socketTimeout != null) { this.socketTimeout = socketTimeout; } else {
	 * this.socketTimeout = DEFAULT_SOCKET_TIMEOUT; }
	 *
	 * return this; }
	 */

	/**
	 * Allow setting an arbitrary HttpClient instance which will be used for
	 * subsequent requests
	 *
	 * @param httpClient
	 * @return
	 */
	public XivelyClient setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;

		return this;
	}

	/**
	 * @return the baseUri
	 */
	public String getBaseUri() {
		return baseUri;
	}

	/**
	 * Build a new HttpClient instance using the timeout variables set on this
	 * instance.
	 *
	 * @return
	 */
	protected HttpClient buildHttpClient() {
		HttpClient hc = new DefaultHttpClient();

		hc.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				this.socketTimeout);
		hc.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
				this.connectionTimeout);

		return hc;
	}

	public FeedService feeds() {
		if (this.feedService == null) {
			this.feedService = new FeedService(this);
		}

		return this.feedService;
	}

	/**
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public XivelyResponse get(XivelyRequest request) throws IOException {
		HttpGet httpGet = new HttpGet(request.generateUri());

		addHeaders(httpGet);

		logger.info(httpGet.toString());

		HttpResponse response = httpClient.execute(httpGet);

		final int statusCode = response.getStatusLine().getStatusCode();

		if (isOk(statusCode)) {
			return new XivelyResponse(response, parseDomainObject(request,
					response));
		} else {
			throw createException(response);
		}
	}

	public XivelyResponse post(XivelyRequest request) throws IOException {
		HttpPost httpPost = new HttpPost(request.generateUri());

		addHeaders(httpPost);

		StringEntity entity = getEntity(request);
		httpPost.setEntity(entity);

		logger.info(httpPost.toString());

		HttpResponse response = httpClient.execute(httpPost);

		if (isOk(response.getStatusLine().getStatusCode())) {
			Object id = extractIdFromLocation(response);

			DomainObject obj = (DomainObject) request.getObject();
			obj.setId(id);

			return new XivelyResponse(response, obj);
		}  else {
			throw createException(response);
		}
	}

	/**
	 * @param response
	 * @return
	 */
	private String extractIdFromLocation(HttpResponse response) {
		Header header = response.getFirstHeader(HEADER_LOCATION);
		String[] tokens = header.getValue().split("/");
		return tokens[tokens.length - 1];
	}

	/**
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public XivelyResponse put(XivelyRequest request) throws IOException {
		HttpPut httpPut = new HttpPut(request.generateUri());

		addHeaders(httpPut);

		StringEntity entity = getEntity(request);
		httpPut.setEntity(entity);

		logger.info(httpPut.toString());

		HttpResponse response = httpClient.execute(httpPut);

		if (isOk(response.getStatusLine().getStatusCode())) {
			return new XivelyResponse(response, request.getObject());
		} else {
			throw createException(response);
		}
	}

	/**
	 * @param request
	 * @return
	 */
	private StringEntity getEntity(XivelyRequest request) throws IOException {
		String json = JsonUtils.toJson(request.getObject());
		return new StringEntity(json);
	}

	/**
	 * @param httpRequest
	 */
	private void addHeaders(HttpRequestBase httpRequest) {
		if (apiKey != null) {
			httpRequest.addHeader(HEADER_API_KEY, apiKey);
		}

		httpRequest.addHeader(HEADER_USER_AGENT, userAgent);
		httpRequest.addHeader(HEADER_ACCEPT, Constants.CONTENT_TYPE_JSON);
	}

	/**
	 * @param response
	 * @return
	 */
	private IOException createException(HttpResponse response) {
		if (isError(response.getStatusLine().getStatusCode())) {
			final ResponseError error;
			try {
				error = parseError(response);
			} catch (IOException e) {
				return e;
			}
			return new RequestException(error, response.getStatusLine()
					.getStatusCode());
		}
		return new IOException(response.getStatusLine().getReasonPhrase());
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	private Object parseDomainObject(XivelyRequest request,
			HttpResponse response) throws IOException {
		Type type = request.getType();
		if (type != null) {
			return parseJson(response, type);
		} else {
			return null;
		}
	}

	/**
	 * @param response
	 * @return
	 */
	private ResponseError parseError(HttpResponse response) throws IOException {
		return parseJson(response, ResponseError.class);
	}

	/**
	 * Extract JSON from entity body, and parse it via GSON.
	 *
	 * @param response
	 * @param type
	 * @return
	 * @throws IOException
	 */
	private <T> T parseJson(HttpResponse response, Type type)
			throws IOException {

		HttpEntity entity = response.getEntity();

		if (entity != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent(), Constants.CHARSET_UTF8), bufferSize);
			try {
				return JsonUtils.fromJson(reader, type);
			} catch (JsonParseException jpe) {
				IOException ioe = new IOException(
						"Parse exception converting JSON to object");
				ioe.initCause(jpe);
				throw ioe;
			} finally {
				reader.close();
			}
		} else {
			return null;
		}
	}

	/**
	 * @param statusCode
	 * @return
	 */
	private boolean isOk(final int statusCode) {
		switch (statusCode) {
		case HTTP_OK:
		case HTTP_CREATED:
			return true;
		default:
			return false;
		}
	}

	/**
	 * @param statusCode
	 * @return
	 */
	private boolean isError(int statusCode) {
		switch (statusCode) {
		case HTTP_BAD_REQUEST:
		case HTTP_FORBIDDEN:
		case HTTP_INTERNAL_SERVER_ERROR:
		case HTTP_NOT_FOUND:
		case HTTP_SERVICE_UNAVAILABLE:
		case HTTP_UNAUTHORIZED:
		case HTTP_UNPROCESSABLE_ENTITY:
			return true;
		default:
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("apiKey", this.apiKey)
				.append("baseUri", this.baseUri)
				.append("userAgent", this.userAgent)
				.append("connectionTimeout", this.connectionTimeout)
				.append("socketTimeout", this.socketTimeout).toString();
	}

}
