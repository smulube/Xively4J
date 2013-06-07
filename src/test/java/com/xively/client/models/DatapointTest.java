// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author sam
 *
 */
public class DatapointTest {

	private Datapoint datapoint1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.datapoint1 = new Datapoint();
		this.datapoint1.setValue("12");
		this.datapoint1.setAt("2013-03-03T12:22:29.192192Z");
	}

	@Test
	@Ignore
	public void test() {
		fail("Not yet implemented");
	}

}
