// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xively.client.XivelyClient;
import com.xively.client.XivelyConstants;
import com.xively.client.http.XivelyRequest;
import com.xively.client.http.XivelyResponse;
import com.xively.client.models.Device;
import com.xively.client.models.Product;
import com.xively.client.utils.HmacUtils;

/**
 * @author sam
 * 
 */
public class DeviceService extends BaseService {

    Logger logger = LoggerFactory.getLogger(DeviceService.class);

    /**
     * 
     */
    public DeviceService() {
        super();
    }

    /**
     * 
     * @param client
     */
    public DeviceService(XivelyClient client) {
        super(client);
    }

    /**
     * 
     * @param product
     * @param device
     * @return
     * @throws IOException
     */
    public Device activate(Product product, Device device) throws IOException {
        checkProduct(product);
        checkDomainObject(device, "Device");
        checkDomainObjectId(device.getId(), "Device");

        if (product.getSecret() == null) {
            throw new IllegalArgumentException("Product secret cannot be null");
        } else if (product.getSecret() == "") {
            throw new IllegalArgumentException("Product secret cannot be empty");
        }

        String activationCode = HmacUtils.activationCode(product.getSecret(), device.getSerial());

        StringBuilder uri = new StringBuilder(this.client.getBaseUri());
        uri.append("/").append(XivelyConstants.SEGMENT_DEVICES);
        uri.append("/").append(activationCode);
        uri.append("/").append(XivelyConstants.SEGMENT_ACTIVATE);

        XivelyRequest request = new XivelyRequest();
        request.setUri(uri);

        XivelyResponse response = this.client.get(request);

        return (Device) response.getBody();
    }

    /**
     * Delete the specified device.
     *
     * @param product
     * @param serial
     * @throws IOException
     */
    public void delete(Product product, String serial) throws IOException {
        checkProduct(product);
        checkDomainObjectId(serial, "Device");

        StringBuilder uri = new StringBuilder(this.client.getBaseUri());
        uri.append("/").append(XivelyConstants.SEGMENT_PRODUCTS);
        uri.append("/").append(product.getId());
        uri.append("/").append(XivelyConstants.SEGMENT_DEVICES);
        uri.append("/").append(serial);

        XivelyRequest request = new XivelyRequest();
        request.setUri(uri);

        this.client.delete(request);
    }

    /**
     * 
     * @param product
     * @param serial
     * @return
     * @throws IOException
     */
    public Device get(Product product, String serial) throws IOException {
        checkProduct(product);
        checkDomainObjectId(serial, "Device");

        StringBuilder uri = new StringBuilder(this.client.getBaseUri());
        uri.append("/").append(XivelyConstants.SEGMENT_PRODUCTS);
        uri.append("/").append(product.getId());
        uri.append("/").append(XivelyConstants.SEGMENT_DEVICES);
        uri.append("/").append(serial);

        XivelyRequest request = new XivelyRequest();
        request.setUri(uri).setType(Device.class);

        XivelyResponse response = this.client.get(request);

        return (Device) response.getBody();
    }

    /**
     * 
     * @param product
     * @param device
     * @return
     * @throws IOException
     */
    public Device update(Product product, Device device) throws IOException {
        checkProduct(product);
        checkDomainObject(device, "Device");
        checkDomainObjectId(device.getId(), "Device");

        StringBuilder uri = new StringBuilder(this.client.getBaseUri());
        uri.append("/").append(XivelyConstants.SEGMENT_PRODUCTS);
        uri.append("/").append(product.getId());
        uri.append("/").append(XivelyConstants.SEGMENT_DEVICES);
        uri.append("/").append(device.getId());

        XivelyRequest request = new XivelyRequest();
        request.setUri(uri).setObject(device);

        return (Device) this.client.put(request).getBody();
    }

    /**
     * @param product
     */
    private void checkProduct(Product product) {
        checkDomainObject(product, "Product");
        checkDomainObjectId(product.getId(), "Product");
    }
}
