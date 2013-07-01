// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.HttpURLConnection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.xively.client.models.Feed;

/**
 * @author sam
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class XivelyResponseTest {

	@Mock
	private HttpURLConnection connection;

	private XivelyResponse response;
	private Feed feed;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.feed = new Feed();
		this.response = new XivelyResponse(connection, feed);
	}

	@Test
	public void domainObject() {
		assertEquals(this.feed, this.response.getBody());
	}

	@Test
	public void getHeader() {
		when(this.connection.getHeaderField("Location")).thenReturn("http://example.com");
		assertEquals("http://example.com", this.response.getHeader("Location"));
		verify(this.connection).getHeaderField("Location");
	}

	@Test
	public void idFromLocation() {
		when(this.connection.getHeaderField("Location")).thenReturn("http://api.xively.com/v2/feeds/123");
		assertEquals("123", this.response.getIdFromLocation());
	}

	@Test
	public void idFromLocationWhenLocationNull() {
		when(this.connection.getHeaderField("Location")).thenReturn(null);
		assertNull(this.response.getIdFromLocation());
	}

	@Test
	public void idFromLocationWhenLocationHasParams() {
		when(this.connection.getHeaderField("Location")).thenReturn("http://api.xively.com/v2/feeds/123?param=true");
		assertEquals("123", this.response.getIdFromLocation());
	}
}
