// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.xively.client.utils.JsonUtils;

/**
 * Unit test of {@link ChannelDefaults}.
 *
 * @author sam
 *
 */
public class ChannelDefaultsTest {

	private String json;

	@Before
	public void setup() {
		this.json = "{\"id\":\"sensor1\",\"tags\":[\"temperature\",\"temp\"],\"unit\":{\"symbol\":\"C\",\"label\":\"Celsius\",\"type\":\"basicSI\"}}";
	}

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
	public void jsonRepresentation() {
		ChannelDefaults cd = new ChannelDefaults();
		List<String> tags = new ArrayList<String>();
		tags.add("temperature");
		tags.add("temp");
		Unit unit = new Unit();
		unit.setLabel("Celsius");
		unit.setSymbol("C");
		unit.setType(Unit.IFCClassification.BASIC_SI);
		cd.setId("sensor1").setTags(tags).setUnit(unit);
		assertEquals(this.json,	JsonUtils.toJson(cd));
	}

	@Test
	public void jsonParsing() {
		ChannelDefaults cd = JsonUtils.fromJson(this.json, ChannelDefaults.class);
		assertEquals("sensor1", cd.getId());
		List<String> tags = new ArrayList<String>();
		tags.add("temperature");
		tags.add("temp");
		assertEquals(tags, cd.getTags());
		Unit unit = new Unit();
		unit.setLabel("Celsius");
		unit.setSymbol("C");
		unit.setType(Unit.IFCClassification.BASIC_SI);
		assertEquals(unit, cd.getUnit());
	}
}
