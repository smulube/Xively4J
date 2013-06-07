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

	public FeedService() {
		super();
	}

	public FeedService(XivelyClient client) {
		super(client);
	}

	public Feed getFeed(Integer id) throws IOException {
		if (id == null) {
			throw new IllegalArgumentException("Feed ID cannot be null");
		}

		StringBuilder uri = new StringBuilder(client.getBaseUri());
		uri.append("/").append(XivelyConstants.SEGMENT_FEEDS);
		uri.append("/").append(id);

		XivelyRequest request = createRequest();
		request.setUri(uri.toString());
		request.setType(Feed.class);

		XivelyResponse response = client.get(request);

		return (Feed) response.getDomainObject();
	}

	public Feed updateFeed(Feed feed) throws IOException {
		Integer id = (Integer) feed.getId();

		if (id == null) {
			throw new IllegalArgumentException("Feed ID cannot be null");
		}

		StringBuilder uri = new StringBuilder(client.getBaseUri());
		uri.append("/").append(XivelyConstants.SEGMENT_FEEDS);
		uri.append("/").append(id);

		XivelyRequest request = createRequest();
		request.setUri(uri.toString());
		request.setObject(feed);

		return (Feed) client.put(request).getDomainObject();
	}

//	public Feed createFeed(Feed feed) throws IOException {
//		StringBuilder uri = new StringBuilder(client.getBaseUri());
//		uri.append("/").append(XivelyConstants.SEGMENT_FEEDS);
//
//		XivelyRequest request = createRequest();
//		request.setUri(uri);
//		request.setObject(feed);
//
//		return (Feed) client.post(request).getDomainObject();
//	}
}
