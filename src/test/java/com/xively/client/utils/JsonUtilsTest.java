// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.utils;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.xively.client.models.Datapoint;
import com.xively.client.models.Datastream;
import com.xively.client.models.Feed;

/**
 * @author sam
 *
 */
public class JsonUtilsTest {

	private Feed feed;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Datapoint datapoint = new Datapoint();
		datapoint.setValue("12");
		datapoint.setAt("2013-03-03T12:22:29.192192Z");

		Datastream datastream = new Datastream();
		datastream.setId("sensor1");
		datastream.setCurrentValue("12.2");
		datastream.setAt("2013-03-03T12:25.83.192094Z");

		List<Datapoint> datapoints = new ArrayList<Datapoint>();
		datapoints.add(datapoint);

		datastream.setDatapoints(datapoints);

		List<Datastream> datastreams = new ArrayList<Datastream>();
		datastreams.add(datastream);

		this.feed = new Feed();
		this.feed.setId(123);
		this.feed.setTitle("Office environment");
		this.feed.setDescription("This is a description");
		this.feed.setDatastreams(datastreams);
	}

	@Test
	public void testParsingAnObjectToString() {
		assertEquals("foo", JsonUtils.toJson(this.feed));
	}

	@Test
	public void testParsingJsonToAnObject() {
		String json = JsonUtils.toJson(this.feed);
		Feed f = JsonUtils.fromJson(json, Feed.class);
		assertEquals("Office environment", f.getTitle());
	}

}
