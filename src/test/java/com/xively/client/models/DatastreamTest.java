// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author sam
 *
 */
public class DatastreamTest {

	private Datastream datastream;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.datastream = new Datastream();
		this.datastream.setId("sensor1");
		this.datastream.setCurrentValue("12.2");
		this.datastream.setMaxValue("100.2");
		this.datastream.setMinValue(2.2);

	}

	@Test
	public void testToJson() {
		assertEquals("bar", this.datastream.toJson());
	}

}
