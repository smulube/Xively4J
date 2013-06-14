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
public class UnitTest {

	private Unit unit;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.unit = new Unit();
		this.unit.setLabel("Celsius");
		this.unit.setSymbol("C");
		this.unit.setType(Unit.IFCClassification.BASIC_SI);
	}

	@Test
	public void testBasicInstantiation() {
		assertEquals("Celsius", this.unit.getLabel());
		assertEquals("C", this.unit.getSymbol());
		assertEquals(Unit.IFCClassification.BASIC_SI, this.unit.getType());
	}

	@Test
	public void testIFCClassificationEnum() {
		assertEquals("basicSI", Unit.IFCClassification.BASIC_SI.getValue());
		assertEquals(Unit.IFCClassification.BASIC_SI,
				Unit.IFCClassification.fromString("basicSI"));
	}

}
