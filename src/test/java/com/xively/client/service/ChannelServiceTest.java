// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.xively.client.XivelyClient;
import com.xively.client.http.XivelyRequest;
import com.xively.client.http.XivelyResponse;
import com.xively.client.models.Channel;
import com.xively.client.models.Feed;

/**
 * @author sam
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ChannelServiceTest {

    @Mock
    private XivelyClient client;

    @Mock
    private XivelyResponse response;

    private ChannelService service;

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullClient() {
        new ChannelService(null);
    }

    @Test
    public void createChannelWithChannelWithEmptyId() throws IOException {
        doReturn("0").when(this.response).getIdFromLocation();
        doReturn(this.response).when(this.client).post(any(XivelyRequest.class));

        Feed feed = new Feed();
        feed.setId("123");
        Channel channel = new Channel();
        channel.setId("");
        this.service.createChannel(feed, channel);
        XivelyRequest request = new XivelyRequest();
        request.setUri("https://api.xively.com/v2/feeds/123/datastreams");
        request.setObject(channel);
        verify(this.client).post(request);
        verify(this.response).getIdFromLocation();
    }

    @Test
    public void createChannelWithChannelWithNullId() throws IOException {
        doReturn("0").when(this.response).getIdFromLocation();
        doReturn(this.response).when(this.client).post(any(XivelyRequest.class));

        Feed feed = new Feed();
        feed.setId("123");
        Channel channel = new Channel();
        this.service.createChannel(feed, channel);
        XivelyRequest request = new XivelyRequest();
        request.setUri("https://api.xively.com/v2/feeds/123/datastreams");
        request.setObject(channel);
        verify(this.client).post(request);
        verify(this.response).getIdFromLocation();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createChannelWithFeedWithEmptyId() throws IOException {
        Feed feed = new Feed();
        feed.setId("");
        Channel channel = new Channel();
        channel.setId("sensor1");
        this.service.createChannel(feed, channel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createChannelWithFeedWithNullId() throws IOException {
        Feed feed = new Feed();
        Channel channel = new Channel();
        channel.setId("sensor1");
        this.service.createChannel(feed, channel);
    }

    @Test
    public void createChannelWithId() throws IOException {
        doReturn("0").when(this.response).getIdFromLocation();
        doReturn(this.response).when(this.client).post(any(XivelyRequest.class));

        Feed feed = new Feed();
        feed.setId("123");
        Channel channel = new Channel();
        channel.setId("sensor1");
        this.service.createChannel(feed, channel);
        XivelyRequest request = new XivelyRequest();
        request.setUri("https://api.xively.com/v2/feeds/123/datastreams");
        request.setObject(channel);
        verify(this.client).post(request);
        verify(this.response).getIdFromLocation();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createChannelWithNullChannel() throws IOException {
        Feed feed = new Feed();
        feed.setId("123");
        this.service.createChannel(feed, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createChannelWithNullFeed() throws IOException {
        Channel channel = new Channel();
        channel.setId("sensor1");
        this.service.createChannel(null, channel);
    }

    @Test
    public void defaultConstructor() {
        assertNotNull(new ChannelService().getClient());
    }

    @Test
    public void deleteChannel() throws IOException {
        Feed feed = new Feed();
        feed.setId("123");
        this.service.deleteChannel(feed, "sensor1");
        XivelyRequest request = new XivelyRequest();
        request.setUri("https://api.xively.com/v2/feeds/123/datastreams/sensor1");

        verify(this.client).delete(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteChannelWithEmptyChannelId() throws IOException {
        Feed feed = new Feed();
        feed.setId("123");
        this.service.deleteChannel(feed, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteChannelWithFeedWithEmptyId() throws IOException {
        Feed feed = new Feed();
        feed.setId("");
        this.service.deleteChannel(feed, "sensor1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteChannelWithFeedWithNullId() throws IOException {
        Feed feed = new Feed();
        this.service.deleteChannel(feed, "sensor1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteChannelWithNullChannelId() throws IOException {
        Feed feed = new Feed();
        feed.setId("123");
        this.service.deleteChannel(feed, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteChannelWithNullFeed() throws IOException {
        this.service.deleteChannel(null, "sensor1");
    }

    @Test
    public void getChannel() throws IOException {
        Feed feed = new Feed();
        feed.setId("123");
        this.service.getChannel(feed, "sensor1");
        XivelyRequest request = new XivelyRequest();
        request.setUri("https://api.xively.com/v2/feeds/123/datastreams/sensor1");
        request.setType(Channel.class);
        verify(this.client).get(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getChannelWithFeedWithEmptyId() throws IOException {
        Feed feed = new Feed();
        feed.setId("");
        this.service.getChannel(feed, "sensor1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getChannelWithFeedWithNullId() throws IOException {
        Feed feed = new Feed();
        this.service.getChannel(feed, "sensor1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getChannelWithNullFeed() throws IOException {
        this.service.getChannel(null, "sensor1");
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        doReturn("https://api.xively.com/v2").when(this.client).getBaseUri();
        doReturn(this.response).when(this.client).get(any(XivelyRequest.class));
        doReturn(this.response).when(this.client).put(any(XivelyRequest.class));

        this.service = new ChannelService(this.client);
    }

    @Test
    public void updateChannel() throws IOException {
        Feed feed = new Feed();
        feed.setId("123");
        Channel channel = new Channel();
        channel.setId("sensor1");
        this.service.updateChannel(feed, channel);

        XivelyRequest request = new XivelyRequest();
        request.setUri("https://api.xively.com/v2/feeds/123/datastreams/sensor1");
        request.setObject(channel);
        verify(this.client).put(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateChannelWithEmptyChannelId() throws IOException {
        Feed feed = new Feed();
        feed.setId("123");
        Channel channel = new Channel();
        channel.setId("");
        this.service.updateChannel(feed, channel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateChannelWithEmptyFeedId() throws IOException {
        Feed feed = new Feed();
        feed.setId("");
        Channel channel = new Channel();
        channel.setId("sensor1");
        this.service.updateChannel(feed, channel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateChannelWithNullChannelId() throws IOException {
        Feed feed = new Feed();
        feed.setId("123");
        Channel channel = new Channel();
        this.service.updateChannel(feed, channel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateChannelWithNullFeed() throws IOException {
        Channel channel = new Channel();
        channel.setId("sensor1");
        this.service.updateChannel(null, channel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateChannelWithNullFeedId() throws IOException {
        Feed feed = new Feed();
        Channel channel = new Channel();
        channel.setId("sensor1");
        this.service.updateChannel(feed, channel);
    }
}
