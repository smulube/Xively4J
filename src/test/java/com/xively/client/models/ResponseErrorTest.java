// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.xively.client.utils.JsonUtils;

/**
 * @author sam
 *
 */
public class ResponseErrorTest {

	private ResponseError error;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.error = JsonUtils.fromJson(
						"{\"title\":\"Unauthorized\",\"errors\":\"You do not have permission to access this resource\"}",
						ResponseError.class);
	}

	@Test
	public void testParsingFromJson() {
		assertEquals("Unauthorized", this.error.getTitle());
		assertEquals("You do not have permission to access this resource", this.error.getMessage());
	}

}
