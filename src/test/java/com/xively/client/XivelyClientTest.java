// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author sam
 *
 */
public class XivelyClientTest {

	private XivelyClient client;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void baseUriDefaultsToXivelyApi() {
		this.client = new XivelyClient();
		assertEquals("https://api.xively.com/v2", this.client.baseUri);
	}

	@Test
	public void baseUriCanBeSet() {
		this.client = new XivelyClient("beta.xively.com");
		assertEquals("https://beta.xively.com/v2", this.client.baseUri);
	}

	@Test
	public void baseUriAndPortCanBeSet() {
		this.client = new XivelyClient("beta.xively.com", 8000);
		assertEquals("https://beta.xively.com:8000/v2", this.client.baseUri);
	}

	@Test
	public void baseUriPortAndSchemeCanBeSet() {
		this.client = new XivelyClient("beta.xively.com", 8000, "http");
		assertEquals("http://beta.xively.com:8000/v2", this.client.baseUri);
	}

}
