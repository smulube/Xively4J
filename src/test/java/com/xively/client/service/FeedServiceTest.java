// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.xively.client.XivelyClient;
import com.xively.client.http.XivelyRequest;
import com.xively.client.http.XivelyResponse;
import com.xively.client.models.Feed;

/**
 * @author sam
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class FeedServiceTest {

	@Mock
	private XivelyClient client;

	//@Mock
	//private XivelyRequest request;

	@Mock
	private XivelyResponse response;

	private FeedService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		doReturn("https://api.xively.com/v2").when(this.client).getBaseUri();
		doReturn(this.response).when(this.client).get(any(XivelyRequest.class));
		doReturn(this.response).when(this.client).put(any(XivelyRequest.class));

		this.service = new FeedService(this.client);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorNullArgument() {
		new FeedService(null);
	}

	@Test
	public void defaultConstructor() {
		assertNotNull(new FeedService().getClient());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getFeedWithNullId() throws IOException {
		this.service.getFeed(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getFeedWithEmptyId() throws IOException {
		this.service.getFeed("");
	}

	@Test
	public void getFeed() throws IOException {
		this.service.getFeed("123");
		XivelyRequest request = new XivelyRequest();
		request.setUri("https://api.xively.com/v2/feeds/123");
		request.setType(Feed.class);
		verify(this.client).get(request);
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNullFeed() throws IOException {
		this.service.updateFeed(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNullFeedId() throws IOException {
		Feed feed = new Feed();
		this.service.updateFeed(feed);
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateEmptyFeedId() throws IOException {
		Feed feed = new Feed();
		feed.setId("");
		this.service.updateFeed(feed);
	}

	@Test
	public void updateFeed() throws IOException {
		Feed feed = new Feed();
		feed.setId("123");
		this.service.updateFeed(feed);
		XivelyRequest request = new XivelyRequest();
		request.setUri("https://api.xively.com/v2/feeds/123").setObject(feed);
		verify(this.client).put(request);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteFeedWithNullId() throws IOException {
		this.service.deleteFeed(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteFeedWithEmptyId() throws IOException {
		this.service.deleteFeed("");
	}

	@Test
	public void deleteFeed() throws IOException {
		this.service.deleteFeed("123");
		XivelyRequest request = new XivelyRequest();
		request.setUri("https://api.xively.com/v2/feeds/123");
		verify(this.client).delete(request);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createFeedWithNullDomainObject() throws IOException {
		this.service.createFeed(null);
	}
}
