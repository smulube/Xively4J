// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.HttpURLConnection;

import org.junit.Before;
import org.junit.Test;

import com.xively.client.models.Feed;

/**
 * @author sam
 *
 */
public class XivelyResponseTest {

	private HttpURLConnection connection;
	private XivelyResponse response;
	private Feed feed;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.connection = mock(HttpURLConnection.class);
		when(this.connection.getHeaderField(anyString())).thenReturn(
				"http://example.com");

		this.feed = new Feed();
		this.response = new XivelyResponse(connection, feed);
	}

	@Test
	public void testDomainObject() {
		assertEquals(this.feed, this.response.getDomainObject());
	}

	@Test
	public void testGetHeader() {
		assertEquals("http://example.com", this.response.getHeader("Location"));
		verify(this.connection).getHeaderField("Location");
	}
}
