// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.xively.client.models.Feed;

/**
 * @author sam
 *
 */
public class XivelyRequestTest {

	private XivelyRequest request;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.request = new XivelyRequest();
	}

	@Test
	public void testCanSetUriWithAString() {
		this.request.setUri("http://example.com");
		assertEquals("http://example.com", this.request.getUri());
	}

	@Test
	public void testCanSetUriWithAStringBuilder() {
		StringBuilder sb = new StringBuilder("http://example.com");
		this.request.setUri(sb);
		assertEquals("http://example.com", this.request.getUri());
	}

	@Test
	public void testCanSetGetType() {
		this.request.setType(Feed.class);
		assertEquals(Feed.class, this.request.getType());
	}

	@Test
	public void testCanSetGetObject() {
		Feed feed = new Feed();
		this.request.setObject(feed);
		assertEquals(feed, this.request.getObject());
	}

	@Test
	public void testReturnsUsefulToString() {
		this.request.setUri("http://example.com");
		this.request.setType(Feed.class);
		assertTrue(this.request.toString().matches(".*uri=http://example.com.*"));
		assertTrue(this.request.toString().matches(".*com.xively.client.models.Feed.*"));
	}

	@Test
	public void testGenerateUri() {
		this.request.setUri("http://example.com");
		assertEquals("http://example.com", this.request.generateUri());
	}
}
