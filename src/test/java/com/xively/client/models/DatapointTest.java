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

	private Datapoint datapoint;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.datapoint = new Datapoint();
		this.datapoint.setValue("12");
		this.datapoint.setAt("2013-03-03T12:22:29.192192Z");
	}

	@Test
	public void defaultInstantiation() {
		assertEquals("12", this.datapoint.getValue());
		assertEquals("2013-03-03T12:22:29.192192Z", this.datapoint.getAt());
	}

	@Test
	public void convenienceInstantiation() {
		Datapoint dp = new Datapoint("13.2", "2013-03-02T00:00:00Z");
		assertEquals("13.2", dp.getValue());
		assertEquals("2013-03-02T00:00:00Z", dp.getAt());
	}

	@Test
	public void getIdReturnsAt() {
		assertEquals(this.datapoint.getAt(), this.datapoint.getId());
	}

	@Test
	public void equalsAndHashCode() {
		Datapoint dp = new Datapoint();
		dp.setAt(this.datapoint.getAt());
		assertEquals(dp, this.datapoint);
		assertEquals(dp.hashCode(), this.datapoint.hashCode());
	}

	@Test
	public void deepEquals() {
		Datapoint dp = new Datapoint();
		assertFalse(this.datapoint.deepEquals(dp));
		dp.setAt(this.datapoint.getAt());
		dp.setValue(this.datapoint.getValue());
		assertTrue(this.datapoint.deepEquals(dp));
	}

	@Test
	public void usefulToStringOutput() {
		assertTrue(this.datapoint.toString().matches(".*Datapoint.*"));
		assertTrue(this.datapoint.toString().matches(".*at=2013-03-03T12:22:29.192192Z.*"));
		assertTrue(this.datapoint.toString().matches(".*value=12.*"));
	}
}
