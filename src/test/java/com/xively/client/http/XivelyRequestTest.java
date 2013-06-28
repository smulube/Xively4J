// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.xively.client.models.Feed;

/**
 * @author sam
 * 
 */
public class XivelyRequestTest {

    private XivelyRequest request;

    @Test
    public void addParam() {
        this.request.setUri("http://example.com");
        this.request.addParam("limit", "100");
        this.request.addParam("order", "DESC");
        assertEquals("http://example.com?limit=100&order=DESC",
                this.request.generateUri());
    }

    @Test
    public void canSetGetObject() {
        Feed feed = new Feed();
        this.request.setObject(feed);
        assertEquals(feed, this.request.getObject());
    }

    @Test
    public void canSetGetType() {
        this.request.setType(Feed.class);
        assertEquals(Feed.class, this.request.getType());
    }

    @Test
    public void canSetGetUri() {
        this.request.setUri("http://example.com");
        assertEquals("http://example.com", this.request.getUri());
    }

    @Test
    public void canSetUriAsStringBuilder() {
        StringBuilder sb = new StringBuilder();
        sb.append("http://example.com");
        this.request.setUri(sb);
        assertEquals("http://example.com", this.request.getUri());
    }

    @Test
    public void equals() {
        this.request.setUri("https://example.com").setType(Feed.class);
        XivelyRequest request2 = new XivelyRequest();
        request2.setUri("https://example.com").setType(Feed.class);
        assertTrue(this.request.equals(request2));
    }

    @Test
    public void generateUri() {
        this.request.setUri("http://example.com");
        assertEquals("http://example.com", this.request.generateUri());
    }

    @Test
    public void hashcode() {
        this.request.setUri("https://example.com").setType(Feed.class);
        XivelyRequest request2 = new XivelyRequest();
        request2.setUri("https://example.com").setType(Feed.class);
        assertTrue(this.request.hashCode() == request2.hashCode());
    }

    @Test
    public void returnsUsefulToString() {
        this.request.setUri("http://example.com");
        this.request.setType(Feed.class);
        assertTrue(this.request.toString()
                .matches(".*uri=http://example.com.*"));
        assertTrue(this.request.toString().matches(
                ".*com.xively.client.models.Feed.*"));
    }

    @Test
    public void setParams() {
        this.request.setUri("http://example.com");
        Map<String, String> params = new HashMap<String, String>();
        params.put("limit", "100");
        params.put("order", "ASC");
        assertEquals(this.request.setParams(params).getParams(), params);
        this.request.setParams(params);
        assertEquals("http://example.com?limit=100&order=ASC",
                this.request.generateUri());
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        this.request = new XivelyRequest();
    }
}
