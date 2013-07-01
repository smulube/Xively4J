// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.reflect.TypeToken;
import com.xively.client.TestHelper;
import com.xively.client.models.Channel;
import com.xively.client.models.ChannelDefaults;
import com.xively.client.models.Datapoint;
import com.xively.client.models.Device;
import com.xively.client.models.Feed;
import com.xively.client.models.FeedDefaults;
import com.xively.client.models.Location;
import com.xively.client.models.Product;
import com.xively.client.models.Unit;

/**
 * @author sam
 * 
 */
public class JsonUtilsTest {

    private Datapoint datapoint;
    private Channel channel;
    private Feed feed;

    @Test
    public void parsingDevice() {
        String deviceJson = TestHelper.loadStringFromFile("device.json");
        Device device = JsonUtils.fromJson(deviceJson, Device.class);
        assertEquals("001", device.getId());
        assertEquals("001", device.getSerial());
        assertEquals("dad1e5b9e04fcf549ef1795abba828902d68fe65",
                device.getActivationCode());
        assertEquals("2013-06-28T10:56:43Z", device.getCreatedAt());
        assertEquals("2013-06-28T10:58:12Z", device.getActivatedAt());
        assertEquals("942452504", device.getFeedId());
        assertEquals("J6YXM1Regk7aBus7tJ3CszBNAPLjfpaBNgNBjsLjANWWOZOi",
                device.getApiKey());
    }

    //    @Test
    //    public void parsingDevices() {
    //        String devicesJson = TestHelper.loadStringFromFile("devices.json");
    //        List<Device> devices = JsonUtils.fromJsonList(devicesJson, Device.class);
    //        assertTrue(devices.size() == 3);
    //    }

    @Test
    public void parsingProduct() {
        String productJson = TestHelper.loadStringFromFile("product.json");
        Product product = JsonUtils.fromJson(productJson, Product.class);
        assertEquals("Product", product.getName());
        assertEquals("Product description", product.getDescription());
        assertEquals("2AKILjrxZpy3CmiTrxmq", product.getId());
        assertEquals("e1c263198afcb4e198145f3e406817c30bde39fb", product.getSecret());
        assertEquals("deploy", product.getState().getValue());
        assertEquals(12, product.getDevicesCount().intValue());
        assertEquals(3, product.getActivatedDevicesCount().intValue());
        FeedDefaults fd = product.getFeedDefaults();
        assertEquals("Product feed", fd.getTitle());
        assertEquals("Product feed description", fd.getDescription());
        assertTrue(fd.getTags().size() == 1);
        assertEquals("product_tag", fd.getTags().get(0));
        assertTrue(fd.isPrivate());
        assertEquals("http://product.com", fd.getWebsite().toString());
        assertTrue(fd.getChannels().size() == 1);
        ChannelDefaults cd = fd.getChannels().get(0);
        assertEquals("sensor1", cd.getId());
        assertTrue(cd.getTags().size() == 2);
        assertEquals(cd.getTags().get(0), "temperature");
        assertEquals(cd.getTags().get(1), "temp");
        Unit unit = new Unit();
        unit.setSymbol("C");
        unit.setLabel("Celsius");
        assertEquals(unit, cd.getUnit());
    }

    @Test
    public void parsingProducts() {
        String productsJson = TestHelper.loadStringFromFile("products.json");
        Type collectionType = new TypeToken<List<Product>>() {}.getType();
        List<Product> products = JsonUtils.fromJson(productsJson, collectionType);
        assertTrue(products.size() == 3);
        Product product = products.get(0);
        assertEquals("Product 0", product.getName());
        assertEquals("Test product.", product.getDescription());
        assertEquals("Dbw-2Mk0II3-ACizxR9k", product.getId());
    }

    //    @Test
    //    public void parsingProducts() {
    //        String productsJson = TestHelper.loadStringFromFile("products.json");
    //        List<Product> products = JsonUtils.fromJsonList(productsJson,
    //                Product.class);
    //        assertTrue(products.size() == 3);
    //        Product product1 = products.get(0);
    //        assertEquals("Product 0", product1.getName());
    //    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.channel = new Channel();
        this.channel.setId("sensor1");
        this.channel.setCurrentValue("12.2");
        this.channel.setAt("2013-03-03T12:25.83.192094Z");

        List<String> tags = new ArrayList<String>();
        tags.add("temperature");
        tags.add("temp");
        this.channel.setTags(tags);

        Unit unit = new Unit();
        unit.setLabel("Celsius");
        unit.setSymbol("C");
        unit.setType(Unit.IFCClassification.BASIC_SI);
        this.channel.setUnit(unit);

        List<Datapoint> datapoints = new ArrayList<Datapoint>();
        this.datapoint = new Datapoint("10", "2013-03-03T11:04:29.377483Z");
        datapoints.add(this.datapoint);
        datapoints.add(new Datapoint("12", "2013-03-03T12:22:29.192192Z"));

        this.channel.setDatapoints(datapoints);

        List<Channel> channels = new ArrayList<Channel>();
        channels.add(this.channel);

        this.feed = new Feed();
        this.feed.setId("123");
        this.feed.setTitle("Office environment");
        this.feed.setDescription("This is a description");
        this.feed.setChannels(channels);

        Location location = new Location();
        location.setName("London");
        location.setDisposition(Location.Disposition.FIXED);
        location.setDomain(Location.Domain.PHYSICAL);
        location.setExposure(Location.Exposure.INDOOR);
        location.setElevation("23.2");
        location.setLatitude(0.01);
        location.setLongitude(53.29);

        this.feed.setLocation(location);
    }

    @Test
    public void testAddsVersionAttribute() {
        String json = JsonUtils.toJson(this.feed);
        assertTrue(json.matches(".*\"version\":\"1\\.0\\.0\".*"));
    }

    @Test
    public void testDatapointSerialization() {
        String json = JsonUtils.toJson(this.datapoint);
        Datapoint parsed = JsonUtils.fromJson(json, Datapoint.class);

        assertEquals(parsed, this.datapoint);
        assertTrue(parsed.deepEquals(this.datapoint));

        assertEquals("10", parsed.getValue());
        assertEquals("2013-03-03T11:04:29.377483Z", parsed.getAt());
    }

    @Test
    public void testDatastreamSerialization() {
        String json = JsonUtils.toJson(this.channel);
        Channel parsed = JsonUtils.fromJson(json, Channel.class);

        assertEquals(parsed, this.channel);
        assertTrue(parsed.deepEquals(this.channel));

        assertEquals("sensor1", parsed.getId());
        assertEquals("12.2", parsed.getCurrentValue());
        assertEquals("2013-03-03T12:25.83.192094Z", parsed.getAt());

        Unit parsedUnit = parsed.getUnit();
        assertEquals("Celsius", parsedUnit.getLabel());
        assertEquals("C", parsedUnit.getSymbol());
        assertEquals(Unit.IFCClassification.BASIC_SI, parsedUnit.getType());

        assertEquals(2, parsed.getDatapoints().size());

        Datapoint parsedDatapoint = parsed.getDatapoints().get(0);
        assertEquals(this.datapoint.getAt(), parsedDatapoint.getAt());
        assertEquals(this.datapoint.getValue(), parsedDatapoint.getValue());

        assertEquals(2, parsed.getTags().size());
        assertEquals("temperature", parsed.getTags().get(0));
        assertEquals("temp", parsed.getTags().get(1));
    }

    @Test
    public void testFeedSerialization() {
        String json = JsonUtils.toJson(this.feed);
        Feed parsed = JsonUtils.fromJson(json, Feed.class);
        assertEquals(parsed, this.feed);
        assertTrue(this.feed.deepEquals(parsed));

        Location parsedLocation = parsed.getLocation();
        assertEquals(parsedLocation.getName(), "London");
        assertEquals(parsedLocation.getElevation(), "23.2");
        assertEquals(parsedLocation.getDomain(), Location.Domain.PHYSICAL);
        assertEquals(parsedLocation.getDisposition(),
                Location.Disposition.FIXED);
        assertEquals(parsedLocation.getExposure(), Location.Exposure.INDOOR);
    }
}
