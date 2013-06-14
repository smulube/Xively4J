// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
		this.datastream.setMaxValue(100.2);
		this.datastream.setMinValue(2.2);
		this.datastream.setAt("2013-05-05T00:00:00Z");
		List<String> tags = new ArrayList<String>();
		tags.add("temperature");
		tags.add("temp");
		this.datastream.setTags(tags);
		Unit unit = new Unit();
		unit.setLabel("Celsius");
		unit.setSymbol("C");
		unit.setType(Unit.IFCClassification.BASIC_SI);
		this.datastream.setUnit(unit);
		List<Datapoint> datapoints = new ArrayList<Datapoint>();
		datapoints.add(new Datapoint("14.2", "2013-04-01T00:00:00Z"));
		datapoints.add(new Datapoint("13.9", "2013-04-01T00:01:00Z"));
		this.datastream.setDatapoints(datapoints);
	}

	@Test
	public void testDefaultConstructor() {
		assertEquals("sensor1", this.datastream.getId());
		assertEquals("12.2", this.datastream.getCurrentValue());
		assertEquals(100.2, this.datastream.getMaxValue(), 0);
		assertEquals(2.2, this.datastream.getMinValue(), 0);
		assertEquals("2013-05-05T00:00:00Z", this.datastream.getAt());
	}

	@Test
	public void testConvenienceConstructor() {
		Datastream ds = new Datastream("sensor1", "13.2", "2013-06-01T01:00:00Z");
		assertEquals("sensor1", ds.getId());
		assertEquals("13.2", ds.getCurrentValue());
		assertEquals("2013-06-01T01:00:00Z", ds.getAt());
	}

	@Test
	public void testEqualsAndHashCode() {
		Datastream ds = new Datastream();
		ds.setId("sensor1");
		assertEquals(ds, this.datastream);
		assertEquals(ds.hashCode(), this.datastream.hashCode());
	}

	@Test
	public void testUsefulToStringOutput() {
		assertTrue(this.datastream.toString().matches(".*Datastream.*"));
		assertTrue(this.datastream.toString().matches(".*id=sensor1.*"));
		assertTrue(this.datastream.toString().matches(".*current_value=12.2.*"));
	}
}