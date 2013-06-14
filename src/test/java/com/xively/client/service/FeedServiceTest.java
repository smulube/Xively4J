// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.xively.client.XivelyClient;
import com.xively.client.http.XivelyRequest;
import com.xively.client.http.XivelyResponse;
import com.xively.client.models.Feed;

/**
 * @author sam
 *
 */
public class FeedServiceTest {

	private FeedService service;
	private XivelyClient client;
	private XivelyRequest request;
	private XivelyResponse response;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.request = mock(XivelyRequest.class);
		this.response = mock(XivelyResponse.class);
		Feed feed = new Feed();
		feed.setId(123);
		feed.setTitle("My title");

		when(this.response.getDomainObject()).thenReturn(feed);

		this.client = mock(XivelyClient.class);
		when(this.client.getBaseUri()).thenReturn("https://api.xively.com/v2");
		when(this.client.get(any(XivelyRequest.class))).thenReturn(this.response);

		this.service = new FeedService(this.client);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testThrowsIllegalArgumentExceptionIfIdNull() throws IOException {
		this.service.getFeed(null);
	}

	@Test
	public void testGetFeedInitializesRequestCorrectly() throws IOException {
		FeedService spy = spy(this.service);
		doReturn(this.request).when(spy).createRequest();

		spy.getFeed(123);

		verify(this.client).getBaseUri();
		verify(this.request).setUri("https://api.xively.com/v2/feeds/123");
		verify(this.request).setType(Feed.class);
	}

	@Test
	public void testGetFeed() throws IOException {
		Feed feed = this.service.getFeed(123);

		assertEquals(123, feed.getId());
		assertEquals("My title", feed.getTitle());
	}

}
