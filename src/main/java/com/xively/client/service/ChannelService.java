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
import com.xively.client.models.Channel;
import com.xively.client.models.Feed;

/**
 * @author sam
 *
 */
public class ChannelService extends BaseService {

	private final Logger logger = LoggerFactory.getLogger(ChannelService.class);

	/**
	 *
	 */
	public ChannelService() {
		super();
	}

	/**
	 * @param client
	 */
	public ChannelService(XivelyClient client) {
		super(client);
	}

	/**
	 *
	 * @param feed
	 * @param id
	 * @return
	 * @throws IOException
	 */
	public Channel getChannel(Feed feed, String id) throws IOException {
		checkDomainObject(feed, "Feed");
		checkDomainObjectId(feed.getId(), "Feed");

		StringBuilder uri = new StringBuilder(client.getBaseUri());
		uri.append("/").append(XivelyConstants.SEGMENT_FEEDS);
		uri.append("/").append(feed.getId());
		uri.append("/").append(XivelyConstants.SEGMENT_DATASTREAMS);
		uri.append("/").append(id);

		XivelyRequest request = new XivelyRequest();
		request.setUri(uri.toString()).setType(Channel.class);

		XivelyResponse response = client.get(request);

		return (Channel) response.getBody();
	}

	/**
	 *
	 * @param feed
	 * @param channel
	 * @return
	 * @throws IOException
	 */
	public Channel updateChannel(Feed feed, Channel channel) throws IOException {
		checkDomainObject(feed, "Feed");
		checkDomainObjectId(feed.getId(), "Feed");

		String id = channel.getId();

		checkDomainObjectId(id, "Channel");

		StringBuilder uri = new StringBuilder(client.getBaseUri());
		uri.append("/").append(XivelyConstants.SEGMENT_FEEDS);
		uri.append("/").append(feed.getId());
		uri.append("/").append(XivelyConstants.SEGMENT_DATASTREAMS);
		uri.append("/").append(id);

		XivelyRequest request = new XivelyRequest();
		request.setUri(uri).setObject(channel);

		return (Channel) client.put(request).getBody();
	}

	/**
	 *
	 * @param feed
	 * @param id
	 * @throws IOException
	 */
	public void deleteChannel(Feed feed, String id) throws IOException {
		checkDomainObject(feed, "Feed");
		checkDomainObjectId(feed.getId(), "Feed");

		checkDomainObjectId(id, "Channel");

		StringBuilder uri = new StringBuilder(client.getBaseUri());
		uri.append("/").append(XivelyConstants.SEGMENT_FEEDS);
		uri.append("/").append(feed.getId());
		uri.append("/").append(XivelyConstants.SEGMENT_DATASTREAMS);
		uri.append("/").append(id);

		XivelyRequest request = new XivelyRequest();
		request.setUri(uri);

		client.delete(request);
	}

	/**
	 *
	 * @param feed
	 * @param channel
	 * @return
	 * @throws IOException
	 */
	public Channel createChannel(Feed feed, Channel channel) throws IOException {
		checkDomainObject(feed, "Feed");
		checkDomainObjectId(feed.getId(), "Feed");

		checkDomainObject(channel, "Channel");

		StringBuilder uri = new StringBuilder(client.getBaseUri());
		uri.append("/").append(XivelyConstants.SEGMENT_FEEDS);
		uri.append("/").append(feed.getId());
		uri.append("/").append(XivelyConstants.SEGMENT_DATASTREAMS);

		XivelyRequest request = new XivelyRequest();
		request.setUri(uri).setObject(channel);

		XivelyResponse response = client.post(request);
		channel.setId(response.getIdFromLocation());

		return channel;
	}
}