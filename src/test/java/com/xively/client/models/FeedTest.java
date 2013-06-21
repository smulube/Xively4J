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
 * Unit tests for {@link Feed} instances.
 *
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
		this.feed.setId("123");
		this.feed.setTitle("Title");
		this.feed.setDescription("Description");
		List<String> tags = new ArrayList<String>();
		tags.add("temperature");
		tags.add("heat");
		this.feed.setTags(tags);
		List<Channel> channels = new ArrayList<Channel>();
		Channel channel = new Channel();
		channel.setId("sensor1");
		channel.setCurrentValue("12.2");
		channels.add(channel);
		Channel channel2 = new Channel();
		channel2.setId("sensor2");
		channel2.setCurrentValue("92.2");
		channels.add(channel2);
		this.feed.setChannels(channels);
		Location location = new Location();
		location.setName("Location");
		location.setLatitude(0.0012);
		location.setLongitude(53.2123);
		this.feed.setLocation(location);
	}

	@Test
	public void gettersSetters() {
		assertEquals(this.feed.getId(), "123");
		assertEquals(this.feed.getTitle(), "Title");
		assertEquals(this.feed.getDescription(), "Description");
	}

	@Test
	public void addChannel() {
		assertTrue(this.feed.getChannels().size() == 2);
		Channel newDatastream = new Channel();
		newDatastream.setId("sensor3");
		this.feed.addChannel(newDatastream);
		assertTrue(this.feed.getChannels().size() == 3);
	}

	@Test
	public void equalsOnId() {
		Feed feed2 = new Feed();
		feed2.setId("123");
		assertEquals(this.feed, feed2);
		feed2.setId(null);
		assertFalse(this.feed.equals(feed2));
	}

	@Test
	public void hashCodeOnId() {
		Feed feed2 = new Feed();
		feed2.setId("123");
		assertTrue(this.feed.hashCode() == feed2.hashCode());
		feed2.setId(null);
		assertFalse(this.feed.hashCode() == feed2.hashCode());
	}

	@Test
	public void deepEquals() {
		Feed feed2 = new Feed();
		feed2.setId("123");
		assertFalse(this.feed.deepEquals(feed2));
		assertTrue(this.feed.deepEquals(this.feed));
	}
}
