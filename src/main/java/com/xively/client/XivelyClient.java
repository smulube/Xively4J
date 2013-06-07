// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonParseException;
import com.xively.client.exceptions.RequestException;
import com.xively.client.http.XivelyRequest;
import com.xively.client.http.XivelyResponse;
import com.xively.client.models.ResponseError;
import com.xively.client.service.FeedService;
import com.xively.client.utils.EncodingUtils;
import com.xively.client.utils.JsonUtils;

/**
 * @author sam
 *
 */
public class XivelyClient {
	public static final String VERSION = "0.1.0-SNAPSHOT";

	protected static final String API_VERSION = "v2";

	protected static final String HEADER_API_KEY = "X-ApiKey";

	protected static final String HEADER_AUTHORIZATION = "Authorization";

	protected static final String HEADER_USER_AGENT = "User-Agent";

	protected static final String HEADER_ACCEPT = "Accept";

	protected static final String HEADER_CONTENT_TYPE = "Content-Type";

	protected static final String HEADER_LOCATION = "Location";

	protected static final String HEADER_CONTENT_LENGTH = "Content-Length";

	protected static final String HTTP_GET = "GET";

	protected static final String HTTP_POST = "POST";

	protected static final String HTTP_PUT = "PUT";

	protected static final String HTTP_DELETE = "DELETE";

	protected static final String USER_AGENT = "Xively4J/" + VERSION;

	protected static final String CONTENT_TYPE_JSON = "application/json";

	protected static final int HTTP_OK = 200;
	protected static final int HTTP_CREATED = 201;
	protected static final int HTTP_BAD_REQUEST = 400;
	protected static final int HTTP_UNAUTHORIZED = 401;
	protected static final int HTTP_FORBIDDEN = 403;
	protected static final int HTTP_NOT_FOUND = 404;
	protected static final int HTTP_UNPROCESSABLE_ENTITY = 422;
	protected static final int HTTP_INTERNAL_SERVER_ERROR = 500;
	protected static final int HTTP_SERVICE_UNAVAILABLE = 503;

	private static final Integer DEFAULT_CONNECT_TIMEOUT = 3000;
	private static final Integer DEFAULT_READ_TIMEOUT = 3000;

	protected final String baseUri;

	private String apiKey;
	private String username;
	private String credentials;
	private String userAgent = USER_AGENT;
	private int connectTimeout;
	private final int readTimeout;

	private FeedService feedService;

	private final int bufferSize = 8192;

	private final Logger logger = LoggerFactory.getLogger(XivelyClient.class);

	public XivelyClient() {
		this(XivelyConstants.API_HOST);
	}

	/**
	 * @param hostname
	 */
	public XivelyClient(String hostname) {
		this(hostname, -1, XivelyConstants.PROTOCOL_HTTPS);
	}

	/**
	 *
	 * @param hostname
	 * @param port
	 */
	public XivelyClient(String hostname, int port) {
		this(hostname, port, XivelyConstants.PROTOCOL_HTTPS);
	}

	/**
	 * @param hostname
	 * @param port
	 * @param scheme
	 */
	public XivelyClient(String hostname, int port, String scheme) {
		this(hostname, port, scheme, DEFAULT_READ_TIMEOUT,
				DEFAULT_CONNECT_TIMEOUT);
	}

	/**
	 *
	 * @param hostname
	 * @param port
	 * @param scheme
	 * @param readTimeout
	 * @param connectTimeout
	 */
	public XivelyClient(String hostname, int port, String scheme,
			int readTimeout, int connectTimeout) {
		StringBuilder uri = new StringBuilder(scheme);
		uri.append("://");
		uri.append(hostname);
		if (port > 0) {
			uri.append(":").append(port);
		}
		uri.append("/").append(API_VERSION);

		this.baseUri = uri.toString();
		this.readTimeout = readTimeout;
		this.connectTimeout = connectTimeout;

		logger.info("XivelyClient initialized: " + this.baseUri);
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
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	public XivelyClient setCredentials(String username, String password) {
		this.username = username;
		StringBuilder sb = new StringBuilder("Basic ");
		sb.append(EncodingUtils.toBase64(username + ":" + password));

		this.credentials = sb.toString();

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

	public XivelyClient setConnectTimeout(final int connectTimeout) {
		if (connectTimeout > 0) {
			this.connectTimeout = connectTimeout;
		} else {
			this.connectTimeout = DEFAULT_CONNECT_TIMEOUT;
		}

		return this;
	}

	/**
	 * @return the baseUri
	 */
	public String getBaseUri() {
		return baseUri;
	}

	/**
	 *
	 * @return
	 */
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
		HttpURLConnection httpResponse = createGet(request.generateUri());

		logger.info("GET " + request.generateUri());

		// implicitly performs the request
		final int statusCode = httpResponse.getResponseCode();

		if (isOk(statusCode)) {
			return new XivelyResponse(httpResponse, parseDomainObject(request,
					httpResponse));
		} else {
			throw createException(httpResponse);
		}
	}

	/**
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public XivelyResponse put(XivelyRequest request) throws IOException {
		HttpURLConnection httpPut = createPut(request.generateUri());

		logger.info("PUT " + request.generateUri());

		sendObject(httpPut, request.getObject());

		if (isOk(httpPut.getResponseCode())) {
			return new XivelyResponse(httpPut, request.getObject());
		} else {
			throw createException(httpPut);
		}
	}

	/**
	 * Actually send a DomainObject to the remote URL
	 *
	 * @param request
	 * @param object
	 * @throws IOException
	 */
	protected void sendObject(HttpURLConnection request, Object object)
			throws IOException {
		request.setDoOutput(true);
		if (object != null) {
			request.setRequestProperty(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON);
			byte[] data = JsonUtils.toJson(object).getBytes(
					XivelyConstants.CHARSET_UTF8);
			request.setFixedLengthStreamingMode(data.length);
			BufferedOutputStream output = new BufferedOutputStream(
					request.getOutputStream(), bufferSize);
			try {
				output.write(data);
				output.flush();
			} finally {
				try {
					output.close();
				} catch (IOException ioe) {
					// Ignored
				}
			}
		} else {
			request.setFixedLengthStreamingMode(0);
			request.setRequestProperty(HEADER_CONTENT_LENGTH, "0");
		}
	}

	/**
	 * @param uri
	 * @return
	 * @throws IOException
	 */
	private HttpURLConnection createPut(String uri) throws IOException {
		return createConnection(uri, HTTP_PUT);
	}

	/**
	 *
	 * @param uri
	 * @return
	 * @throws IOException
	 */
	private HttpURLConnection createPost(String uri) throws IOException {
		return createConnection(uri, HTTP_POST);
	}

	/**
	 *
	 * @param uri
	 * @return
	 * @throws IOException
	 */
	private HttpURLConnection createDelete(String uri) throws IOException {
		return createConnection(uri, HTTP_DELETE);
	}

	/**
	 * @param request
	 * @return
	 */
	private InputStream createResponseStream(HttpURLConnection request)
			throws IOException {
		return request.getInputStream();
	}

	/**
	 * @param uri
	 * @return
	 * @throws IOException
	 */
	private HttpURLConnection createGet(String uri) throws IOException {
		return createConnection(uri, HTTP_GET);
	}

	/**
	 * Create connection to URI.
	 *
	 * @param uri
	 * @return
	 */
	public HttpURLConnection createConnection(String uri) throws IOException {
		URL url = new URL(uri);
		return (HttpURLConnection) url.openConnection();
	}

	/**
	 * @param uri
	 * @param method
	 * @return
	 * @throws IOException
	 */
	private HttpURLConnection createConnection(String uri, String method)
			throws IOException {
		HttpURLConnection connection = createConnection(uri);
		connection.setRequestMethod(method);
		return configureRequest(connection);
	}

	/**
	 * @param connection
	 * @return
	 */
	private HttpURLConnection configureRequest(
			final HttpURLConnection connection) {
		if (this.apiKey != null) {
			connection.setRequestProperty(HEADER_API_KEY, this.apiKey);
		} else if (this.credentials != null) {
			connection.setRequestProperty(HEADER_AUTHORIZATION,
					this.credentials);
		}
		connection.setRequestProperty(HEADER_USER_AGENT, this.userAgent);
		connection.setRequestProperty(HEADER_ACCEPT, "application/json");
		connection.setReadTimeout(this.readTimeout);
		connection.setConnectTimeout(this.connectTimeout);

		return connection;
	}

	// public XivelyResponse post(XivelyRequest request) throws IOException {
	// HttpPost httpPost = new HttpPost(request.generateUri());
	//
	// addHeaders(httpPost);
	//
	// StringEntity entity = getEntity(request);
	// httpPost.setEntity(entity);
	//
	// logger.info(httpPost.toString());
	//
	// HttpResponse response = httpClient.execute(httpPost);
	//
	// if (isOk(response.getStatusLine().getStatusCode())) {
	// Object id = extractIdFromLocation(response);
	//
	// DomainObject obj = (DomainObject) request.getObject();
	// obj.setId(id);
	//
	// return new XivelyResponse(response, obj);
	// } else {
	// throw createException(response);
	// }
	// }

	/**
	 * @param response
	 * @return
	 */
	private String extractIdFromLocation(HttpURLConnection response) {
		String location = response.getHeaderField(HEADER_LOCATION);
		if (location != null) {
			String[] tokens = location.split("/");
			return tokens[tokens.length - 1];
		} else {
			return null;
		}
	}

	/**
	 * @param response
	 * @return
	 */
	private IOException createException(HttpURLConnection response) {
		try {
			if (isError(response.getResponseCode())) {
				final ResponseError error;
				try {
					error = parseError(response);
				} catch (IOException e) {
					return e;
				}
				return new RequestException(error, response.getResponseCode());
			}
			return new IOException(response.getResponseMessage());
		} catch (IOException ioe) {
			return ioe;
		}

	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	private Object parseDomainObject(XivelyRequest request,
			HttpURLConnection response) throws IOException {
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
	private ResponseError parseError(HttpURLConnection response)
			throws IOException {
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
	private <T> T parseJson(HttpURLConnection response, Type type)
			throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				response.getInputStream()));

		try {
			return JsonUtils.fromJson(reader, type);
		} catch (JsonParseException jpe) {
			IOException ioe = new IOException(
					"Parse error converting JSON to object");
			ioe.initCause(jpe);
			throw ioe;
		} finally {
			reader.close();
		}
	}

	/**
	 *
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
				.append("username", this.username)
				.append("credentials", this.credentials)
				.append("baseUri", this.baseUri)
				.append("userAgent", this.userAgent)
				.append("readTimeout", this.readTimeout)
				.append("connectTimeout", this.connectTimeout).toString();
	}
}
