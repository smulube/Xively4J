// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xively.client.XivelyClient;
import com.xively.client.XivelyConstants;
import com.xively.client.http.XivelyRequest;
import com.xively.client.http.XivelyResponse;
import com.xively.client.models.Feed;

/**
 * @author sam
 *
 */
public class FeedService extends BaseService {

	private final Logger logger = LoggerFactory.getLogger(FeedService.class);

	/**
	 * Default constructor.
	 */
	public FeedService() {
		super();
	}

	/**
	 * Constructor that takes an explicit {@link XivelyClient} instance.
	 *
	 * @param client
	 */
	public FeedService(XivelyClient client) {
		super(client);
	}

	/**
	 *
	 * @param id
	 * @return
	 * @throws IOException
	 */
	public Feed getFeed(String id) throws IOException {
		checkDomainObjectId(id, "Feed");

		StringBuilder uri = new StringBuilder(client.getBaseUri());
		uri.append("/").append(XivelyConstants.SEGMENT_FEEDS);
		uri.append("/").append(id);

		XivelyRequest request = new XivelyRequest();
		request.setUri(uri.toString()).setType(Feed.class);

		XivelyResponse response = client.get(request);

		return (Feed) response.getBody();
	}

	/**
	 *
	 * @param feed
	 * @return
	 * @throws IOException
	 */
	public Feed updateFeed(Feed feed) throws IOException {
		checkDomainObject(feed, "Feed");

		String id = feed.getId();

		checkDomainObjectId(id, "Feed");

		StringBuilder uri = new StringBuilder(client.getBaseUri());
		uri.append("/").append(XivelyConstants.SEGMENT_FEEDS);
		uri.append("/").append(id);

		XivelyRequest request = new XivelyRequest();
		request.setUri(uri);
		request.setObject(feed);

		return (Feed) client.put(request).getBody();
	}

	/**
	 *
	 * @param id
	 * @throws IOException
	 */
	public void deleteFeed(String id) throws IOException {
		checkDomainObjectId(id, "Feed");

		StringBuilder uri = new StringBuilder(client.getBaseUri());
		uri.append("/").append(XivelyConstants.SEGMENT_FEEDS);
		uri.append("/").append(id);

		XivelyRequest request = new XivelyRequest();
		request.setUri(uri);

		client.delete(request);
	}

	public Feed createFeed(Feed feed) throws IOException {
		if (feed == null) {
			throw new IllegalArgumentException("Feed cannot be null");
		}

		StringBuilder uri = new StringBuilder(client.getBaseUri());
		uri.append("/").append(XivelyConstants.SEGMENT_FEEDS);

		XivelyRequest request = new XivelyRequest();
		request.setUri(uri).setObject(feed);

		XivelyResponse response = client.post(request);

		return (Feed) client.post(request).getBody();
	}
}
