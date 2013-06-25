// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

import org.junit.Test;

/**
 * @author sam
 *
 */
public class FeedDefaultsTest {

	@Test
	public void defaultState() {
		FeedDefaults fd = new FeedDefaults();
		assertNull(fd.getTitle());
		assertNull(fd.getDescription());
		assertNull(fd.getTags());
		assertNull(fd.getWebsite());
		assertNull(fd.getChannels());
	}

	@Test
	public void updatingFields() throws URISyntaxException {
		FeedDefaults fd = new FeedDefaults();
		assertEquals("title", fd.setTitle("title").getTitle());
		assertEquals("description", fd.setDescription("description")
				.getDescription());
		assertEquals(new URI("http://example.com"),
				fd.setWebsite(new URI("http://example.com")).getWebsite());
		assertEquals(Collections.<String> emptyList(),
				fd.setTags(Collections.<String> emptyList()).getTags());
		assertEquals(Collections.<ChannelDefaults> emptyList(),
				fd.setChannels(Collections.<ChannelDefaults> emptyList())
						.getChannels());
	}

}
