// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author sam
 *
 */
public class FeedTest {

	private Feed feed;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.feed = new Feed();
		this.feed.setId(123);
		this.feed.setTitle("Title");
		this.feed.setDescription("Description");
		List<String> tags = new ArrayList<String>();
		tags.add("temperature");
		tags.add("heat");
		this.feed.setTags(tags);
		List<Datastream> datastreams = new ArrayList<Datastream>();
		Datastream datastream = new Datastream();
		datastream.setId("sensor1");
		datastream.setCurrentValue("12.2");
		datastreams.add(datastream);
		Datastream datastream2 = new Datastream();
		datastream2.setId("sensor2");
		datastream2.setCurrentValue("92.2");
		datastreams.add(datastream2);
		this.feed.setDatastreams(datastreams);
		Location location = new Location();
		location.setName("Location");
		location.setLatitude(0.0012);
		location.setLongitude(53.2123);
		this.feed.setLocation(location);
	}

	@Test
	public void testGettersSetters() {
		assertEquals(this.feed.getId(), 123);
		assertEquals(this.feed.getTitle(), "Title");
		assertEquals(this.feed.getDescription(), "Description");
	}

	@Test
	public void testAddDatastream() {
		assertTrue(this.feed.getDatastreams().size() == 2);
		Datastream newDatastream = new Datastream();
		newDatastream.setId("sensor3");
		this.feed.addDatastream(newDatastream);
		assertTrue(this.feed.getDatastreams().size() == 3);
	}

	@Test
	public void testEquals() {
		Feed feed2 = new Feed();
		feed2.setId(123);
		assertEquals(this.feed, feed2);
		feed2.setId(null);
		assertFalse(this.feed.equals(feed2));
	}

	@Test
	public void testHashCode() {
		Feed feed2 = new Feed();
		feed2.setId(123);
		assertTrue(this.feed.hashCode() == feed2.hashCode());
		feed2.setId(null);
		assertFalse(this.feed.hashCode() == feed2.hashCode());
	}

	@Test
	public void testDeepEquals() {
		Feed feed2 = new Feed();
		feed2.setId(123);
		assertFalse(this.feed.deepEquals(feed2));
		assertTrue(this.feed.deepEquals(this.feed));
	}
}
