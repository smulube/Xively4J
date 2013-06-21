// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.xively.client.models.Datapoint;
import com.xively.client.models.Datastream;
import com.xively.client.models.Feed;
import com.xively.client.models.Location;
import com.xively.client.models.Unit;

/**
 * @author sam
 *
 */
public class JsonUtilsTest {

	private Datapoint datapoint;
	private Datastream datastream;
	private Feed feed;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.datastream = new Datastream();
		this.datastream.setId("sensor1");
		this.datastream.setCurrentValue("12.2");
		this.datastream.setAt("2013-03-03T12:25.83.192094Z");

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
		this.datapoint = new Datapoint("10", "2013-03-03T11:04:29.377483Z");
		datapoints.add(this.datapoint);
		datapoints.add(new Datapoint("12", "2013-03-03T12:22:29.192192Z"));

		this.datastream.setDatapoints(datapoints);

		List<Datastream> datastreams = new ArrayList<Datastream>();
		datastreams.add(datastream);

		this.feed = new Feed();
		this.feed.setId("123");
		this.feed.setTitle("Office environment");
		this.feed.setDescription("This is a description");
		this.feed.setDatastreams(datastreams);

		Location location = new Location();
		location.setName("London");
		location.setDisposition(Location.Disposition.FIXED);
		location.setDomain(Location.Domain.PHYSICAL);
		location.setExposure(Location.Exposure.INDOOR);
		location.setElevation("23.2");
		location.setLatitude(0.01);
		location.setLongitude(53.29);

		this.feed.setLocation(location);
	}

	@Test
	public void testDatapointSerialization() {
		String json = JsonUtils.toJson(this.datapoint);
		Datapoint parsed = JsonUtils.fromJson(json, Datapoint.class);

		assertEquals(parsed, this.datapoint);
		assertTrue(parsed.deepEquals(this.datapoint));

		assertEquals("10", parsed.getValue());
		assertEquals("2013-03-03T11:04:29.377483Z", parsed.getAt());
	}


	@Test
	public void testDatastreamSerialization() {
		String json = JsonUtils.toJson(this.datastream);
		Datastream parsed = JsonUtils.fromJson(json, Datastream.class);

		assertEquals(parsed, this.datastream);
		assertTrue(parsed.deepEquals(this.datastream));

		assertEquals("sensor1", parsed.getId());
		assertEquals("12.2", parsed.getCurrentValue());
		assertEquals("2013-03-03T12:25.83.192094Z", parsed.getAt());

		Unit parsedUnit = parsed.getUnit();
		assertEquals("Celsius", parsedUnit.getLabel());
		assertEquals("C", parsedUnit.getSymbol());
		assertEquals(Unit.IFCClassification.BASIC_SI, parsedUnit.getType());

		assertEquals(2, parsed.getDatapoints().size());

		Datapoint parsedDatapoint = parsed.getDatapoints().get(0);
		assertEquals(this.datapoint.getAt(), parsedDatapoint.getAt());
		assertEquals(this.datapoint.getValue(), parsedDatapoint.getValue());

		assertEquals(2, parsed.getTags().size());
		assertEquals("temperature", parsed.getTags().get(0));
		assertEquals("temp", parsed.getTags().get(1));
	}

	@Test
	public void testFeedSerialization() {
		String json = JsonUtils.toJson(this.feed);
		Feed parsed = JsonUtils.fromJson(json, Feed.class);
		assertEquals(parsed, this.feed);
		assertTrue(this.feed.deepEquals(parsed));

		Location parsedLocation = parsed.getLocation();
		assertEquals(parsedLocation.getName(), "London");
		assertEquals(parsedLocation.getElevation(), "23.2");
		assertEquals(parsedLocation.getDomain(), Location.Domain.PHYSICAL);
		assertEquals(parsedLocation.getDisposition(), Location.Disposition.FIXED);
		assertEquals(parsedLocation.getExposure(), Location.Exposure.INDOOR);
	}


	@Test
	public void testAddsVersionAttribute() {
		String json = JsonUtils.toJson(this.feed);
		assertTrue(json.matches(".*\"version\":\"1\\.0\\.0\".*"));
	}

}
