// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
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
	public void testDefaultInstantiation() {
		assertEquals("12", this.datapoint1.getValue());
		assertEquals("2013-03-03T12:22:29.192192Z", this.datapoint1.getAt());
	}

	@Test
	public void testConvenienceInstantiation() {
		Datapoint dp = new Datapoint("13.2", "2013-03-02T00:00:00Z");
		assertEquals("13.2", dp.getValue());
		assertEquals("2013-03-02T00:00:00Z", dp.getAt());
	}

	@Test
	public void testEqualsAndHashCode() {
		Datapoint dp = new Datapoint();
		dp.setAt(this.datapoint1.getAt());
		assertEquals(dp, this.datapoint1);
		assertEquals(dp.hashCode(), this.datapoint1.hashCode());
	}

	@Test
	public void testDeepEquals() {
		Datapoint dp = new Datapoint();
		assertFalse(this.datapoint1.deepEquals(dp));
		dp.setAt(this.datapoint1.getAt());
		dp.setValue(this.datapoint1.getValue());
		assertTrue(this.datapoint1.deepEquals(dp));
	}

	@Test
	public void testUsefulToStringOutput() {
		assertTrue(this.datapoint1.toString().matches(".*Datapoint.*"));
		assertTrue(this.datapoint1.toString().matches(".*at=2013-03-03T12:22:29.192192Z.*"));
		assertTrue(this.datapoint1.toString().matches(".*value=12.*"));
	}
}
