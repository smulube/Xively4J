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
public class ChannelTest {

	private Channel channel;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.channel = new Channel();
		this.channel.setId("sensor1");
		this.channel.setCurrentValue("12.2");
		this.channel.setMaxValue(100.2);
		this.channel.setMinValue(2.2);
		this.channel.setAt("2013-05-05T00:00:00Z");
		List<String> tags = new ArrayList<String>();
		tags.add("temperature");
		tags.add("temp");
		this.channel.setTags(tags);
		Unit unit = new Unit();
		unit.setLabel("Celsius");
		unit.setSymbol("C");
		unit.setType(Unit.IFCClassification.BASIC_SI);
		this.channel.setUnit(unit);
		List<Datapoint> datapoints = new ArrayList<Datapoint>();
		datapoints.add(new Datapoint("14.2", "2013-04-01T00:00:00Z"));
		datapoints.add(new Datapoint("13.9", "2013-04-01T00:01:00Z"));
		this.channel.setDatapoints(datapoints);
	}

	@Test
	public void defaultConstructor() {
		assertEquals("sensor1", this.channel.getId());
		assertEquals("12.2", this.channel.getCurrentValue());
		assertEquals(100.2, this.channel.getMaxValue(), 0);
		assertEquals(2.2, this.channel.getMinValue(), 0);
		assertEquals("2013-05-05T00:00:00Z", this.channel.getAt());
	}

	@Test
	public void convenienceConstructor() {
		Channel ds = new Channel("sensor1", "13.2", "2013-06-01T01:00:00Z");
		assertEquals("sensor1", ds.getId());
		assertEquals("13.2", ds.getCurrentValue());
		assertEquals("2013-06-01T01:00:00Z", ds.getAt());
	}

	@Test
	public void equalsAndHashCode() {
		Channel ds = new Channel();
		ds.setId("sensor1");
		assertEquals(ds, this.channel);
		assertEquals(ds.hashCode(), this.channel.hashCode());
	}

	@Test
	public void usefulToStringOutput() {
		assertTrue(this.channel.toString().matches(".*Channel.*"));
		assertTrue(this.channel.toString().matches(".*id=sensor1.*"));
		assertTrue(this.channel.toString().matches(".*current_value=12.2.*"));
	}
}