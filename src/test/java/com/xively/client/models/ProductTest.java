// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.xively.client.TestHelper;
import com.xively.client.utils.JsonUtils;

/**
 * @author sam
 *
 */
public class ProductTest {

	private String json;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.json = TestHelper.loadStringFromFile("product.json");
	}

	@Test
	public void defaultState() {
		Product product = new Product();
		assertNull(product.getName());
		assertNull(product.getDescription());
		assertNull(product.getId());
		assertNull(product.getState());
		assertNull(product.getSecret());
		assertNull(product.getDevicesCount());
		assertNull(product.getActivatedDevicesCount());
		assertNull(product.getFeedDefaults());
	}

	@Test
	public void updatingFields() {
		Product product = new Product();
		assertEquals("name", product.setName("name").getName());
		assertEquals("description", product.setDescription("description")
				.getDescription());
		assertEquals(new FeedDefaults(),
				product.setFeedDefaults(new FeedDefaults()).getFeedDefaults());
		assertEquals("deploy", product.setState("deploy").getState());
	}

	@Test
	@Ignore
	public void parsingJson() {
		Product product = JsonUtils.fromJson(this.json, Product.class);
		assertEquals("Product", product.getName());
		assertEquals("Product description", product.getDescription());
		assertEquals("2AKILjrxZpy3CmiTrxmq", product.getId());

	}
}
