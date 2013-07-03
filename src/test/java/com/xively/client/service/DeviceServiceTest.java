// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.xively.client.TestHelper;
import com.xively.client.XivelyClient;
import com.xively.client.http.XivelyRequest;
import com.xively.client.http.XivelyResponse;
import com.xively.client.models.Device;
import com.xively.client.models.Product;
import com.xively.client.utils.JsonUtils;

/**
 * @author sam
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class DeviceServiceTest {

    @Mock
    private XivelyClient client;

    @Mock
    private XivelyResponse response;

    private DeviceService service;

    @Test
    public void activateDevice() throws IOException {
        String json = TestHelper.loadStringFromFile("product.json");
        Product product = JsonUtils.fromJson(json, Product.class);

        Device device = new Device();
        device.setId("def456");
        this.service.activate(product, device);

        XivelyRequest request = new XivelyRequest();
        request.setUri("https://api.xively.com/v2/devices/7b85b6bd5c211941422d3c95352d915750c11053/activate");

        verify(this.client).get(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void activateWithEmptyProductSecret() throws IOException {
        Product product = new Product();
        Device device = new Device();
        device.setId("def456");
        this.service.activate(product, device);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void activateWithNullProduct() throws IOException {

    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void activateWithNullProductSecret() throws IOException {

    }

    @Test
    public void constructorDefault() {
        assertNotNull(new DeviceService().getClient());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullClient() {
        new DeviceService(null);
    }

    @Test
    public void constructorWithParam() {
        assertEquals(this.client, this.service.getClient());
    }

    @Test
    public void deleteDevice() throws IOException {
        Product product = new Product();
        product.setId("123abc");

        this.service.delete(product, "def456");

        XivelyRequest request = new XivelyRequest();
        request.setUri("https://api.xively.com/v2/products/123abc/devices/def456");
        verify(this.client).delete(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteWithEmptyProductId() throws IOException {
        Product product = new Product();
        product.setId("");
        this.service.delete(null, "001");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteWithNullProduct() throws IOException {
        this.service.delete(null, "001");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteWithNullProductId() throws IOException {
        Product product = new Product();
        this.service.delete(product, "001");
    }

    @Test
    public void getDevice() throws IOException {
        Product product = new Product();
        product.setId("123abc");

        this.service.get(product, "def456");
        XivelyRequest request = new XivelyRequest();
        request.setUri("https://api.xively.com/v2/products/123abc/devices/def456");
        request.setType(Device.class);
        verify(this.client).get(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWithEmptyProductId() throws IOException {
        Product product = new Product();
        product.setId("");
        this.service.get(product, "001");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWithEmptySerial() throws IOException {
        Product product = new Product();
        product.setId("123abc");
        this.service.get(product, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWithNullProduct() throws IOException {
        this.service.get(null, "001");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWithNullProductId() throws IOException {
        Product product = new Product();
        this.service.get(product, "001");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWithNullSerial() throws IOException {
        Product product = new Product();
        product.setId("123abc");
        this.service.get(product, null);
    }

    @Before
    public void setup() throws IOException {
        doReturn("https://api.xively.com/v2").when(this.client).getBaseUri();
        doReturn(this.response).when(this.client).get(any(XivelyRequest.class));
        doReturn(this.response).when(this.client).put(any(XivelyRequest.class));

        this.service = new DeviceService(this.client);
    }

    @Test
    public void updateDevice() throws IOException {
        Product product = new Product();
        product.setId("123abc");
        Device device = new Device();
        device.setId("def456");
        this.service.update(product, device);

        XivelyRequest request = new XivelyRequest();
        request.setUri("https://api.xively.com/v2/products/123abc/devices/def456");
        request.setObject(device);
        verify(this.client).put(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateWithEmptyDeviceId() throws IOException {
        Product product = new Product();
        product.setId("123abc");
        Device device = new Device();
        device.setId("");
        this.service.update(product, device);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateWithEmptyProductId() throws IOException {
        Product product = new Product();
        product.setId("");
        Device device = new Device();
        device.setSerial("def456");
        this.service.update(product, device);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateWithNullDevice() throws IOException {
        Product product = new Product();
        product.setId("123abc");
        this.service.update(product, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateWithNullProduct() throws IOException {
        Device device = new Device();
        device.setSerial("def456");
        this.service.update(null, device);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateWithNullProductId() throws IOException {
        Product product = new Product();
        Device device = new Device();
        device.setSerial("def456");
        this.service.update(product, device);
    }
}
