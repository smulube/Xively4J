// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.Test;

/**
 * Unit test of {@link ChannelDefaults}.
 *
 * @author sam
 *
 */
public class ChannelDefaultsTest {

	@Test
	public void defaultState() {
		ChannelDefaults cd = new ChannelDefaults();
		assertNull(cd.getId());
		assertNull(cd.getTags());
		assertNull(cd.getUnit());
	}

	@Test
	public void updatingFields() {
		ChannelDefaults cd = new ChannelDefaults();
		assertEquals("sensor1", cd.setId("sensor1").getId());
		assertEquals(Collections.<String> emptyList(),
				cd.setTags(Collections.<String> emptyList()).getTags());
		assertEquals(new Unit(), cd.setUnit(new Unit()).getUnit());
	}

	@Test
	public void usefulToString() {
		ChannelDefaults cd = new ChannelDefaults();
		cd.setId("sensor1");
		assertTrue(cd.toString().matches(".+id=sensor1.+"));
	}
}
