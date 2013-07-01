// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.xively.client.TestHelper;
import com.xively.client.utils.JsonUtils;

/**
 * @author sam
 *
 */
public class ProductTest {

    private String json;
    private Reader productsJson;

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
    public void parsingFullJson() throws URISyntaxException {
        Product product = JsonUtils.fromJson(this.json, Product.class);
        assertEquals("Product", product.getName());
        assertEquals("Product description", product.getDescription());
        assertEquals("2AKILjrxZpy3CmiTrxmq", product.getId());
        assertEquals("e1c263198afcb4e198145f3e406817c30bde39fb",
                product.getSecret());
        assertEquals(Product.State.DEPLOY, product.getState());
        assertTrue(product.getDevicesCount().intValue() == 12);
        assertTrue(product.getActivatedDevicesCount().intValue() == 3);

        FeedDefaults fd = product.getFeedDefaults();

        assertEquals("Product feed", fd.getTitle());
        assertEquals("Product feed description", fd.getDescription());
        List<String> feedTags = new ArrayList<String>();
        feedTags.add("product_tag");
        assertEquals(feedTags, fd.getTags());

        assertTrue(fd.isPrivate());
        assertEquals(new URI("http://product.com"), fd.getWebsite());

        assertTrue(fd.getChannels().size() == 1);

        ChannelDefaults cd = fd.getChannels().get(0);
        assertEquals("sensor1", cd.getId());

        List<String> channelTags = new ArrayList<String>();
        channelTags.add("temperature");
        channelTags.add("temp");

        assertEquals(channelTags, cd.getTags());

        Unit unit = new Unit();
        unit.setLabel("Celsius");
        unit.setSymbol("C");

        assertEquals(unit, cd.getUnit());

        assertEquals("username", product.getUser());
    }

    @Test
    public void parsingList() {
        //List<Product> products = JsonUtils.fromJson(this.productsJson, Product.class, Product.class);
    }

    @Test
    public void serializingToJson() throws URISyntaxException {
        Product product = new Product();
        product.setName("New Batch");
        product.setDescription("New description");

        FeedDefaults feedDefaults = new FeedDefaults();
        feedDefaults.setTitle("New feed");
        feedDefaults.setDescription("New feed description");
        feedDefaults.setPrivate(false);
        feedDefaults.setWebsite(new URI("http://example.com"));

        List<String> feedTags = new ArrayList<String>();
        feedTags.add("feed_tag");
        feedDefaults.setTags(feedTags);

        List<ChannelDefaults> channels = new ArrayList<ChannelDefaults>();

        ChannelDefaults cd = new ChannelDefaults();
        cd.setId("channel1");
        List<String> channelTags = new ArrayList<String>();
        channelTags.add("channel_tag1");
        channelTags.add("channel_tag2");
        cd.setTags(channelTags);
        Unit unit = new Unit();
        unit.setLabel("Celsius");
        unit.setSymbol("C");
        cd.setUnit(unit);

        channels.add(cd);

        feedDefaults.setChannels(channels);
        product.setFeedDefaults(feedDefaults);

        String json = JsonUtils.toJson(product);

        Product parsedProduct = JsonUtils.fromJson(json, Product.class);

        assertEquals(parsedProduct.getName(), product.getName());
        assertEquals(parsedProduct.getDescription(), product.getDescription());
        assertEquals(parsedProduct.getFeedDefaults(), product.getFeedDefaults());
    }

    @Test
    public void serializingWithoutFeedDefaults() {
        Product product = new Product();
        product.setName("name");
        product.setDescription("description");

        String json = JsonUtils.toJson(product);

        Product parsedProduct = JsonUtils.fromJson(json, Product.class);

        assertEquals("name", parsedProduct.getName());
        assertEquals("description", parsedProduct.getDescription());
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.json = TestHelper.loadStringFromFile("product.json");
    }

    @Test
    public void updatingFields() {
        Product product = new Product();
        assertEquals("name", product.setName("name").getName());
        assertEquals("description", product.setDescription("description")
                .getDescription());
        assertEquals(new FeedDefaults(),
                product.setFeedDefaults(new FeedDefaults()).getFeedDefaults());
    }
}
