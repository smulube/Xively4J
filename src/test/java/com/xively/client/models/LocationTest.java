// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author sam
 *
 */
public class LocationTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDomainEnum() {
		assertEquals("physical", Location.Domain.PHYSICAL.getValue());
		assertEquals(Location.Domain.PHYSICAL, Location.Domain.fromString("physical"));
		assertEquals(Location.Domain.VIRTUAL, Location.Domain.fromString("VIRTUAL"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDomainEnumFromStringWhenNotFound() {
		Location.Domain.fromString("mystical");
	}

	@Test(expected = NullPointerException.class)
	public void testDomainEnumFromStringWhenNull() {
		Location.Domain.fromString(null);
	}

	@Test
	public void testExposureEnum() {
		assertEquals("indoor", Location.Exposure.INDOOR.getValue());
		assertEquals(Location.Exposure.OUTDOOR, Location.Exposure.fromString("outdoor"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExposureEnumFromStringWhenNotFound() {
		Location.Exposure.fromString("underwater");
	}

	@Test(expected = NullPointerException.class)
	public void testExposureEnumFromStringWhenNull() {
		Location.Exposure.fromString(null);
	}


	@Test
	public void testDispositionEnum() {
		assertEquals("fixed", Location.Disposition.FIXED.getValue());
		assertEquals(Location.Disposition.MOBILE, Location.Disposition.fromString("mobile"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDispositionEnumFromStringWhenNotFound() {
		Location.Disposition.fromString("fungible");
	}

	@Test(expected = NullPointerException.class)
	public void testDispositionEnumFromStringWhenNull() {
		Location.Disposition.fromString(null);
	}
}
