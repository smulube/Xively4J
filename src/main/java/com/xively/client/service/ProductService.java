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
import com.xively.client.models.Product;

/**
 * @author sam
 *
 */
public class ProductService extends BaseService {

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    /**
     * Default constructor.
     */
    public ProductService() {
        super();
    }

    /**
     * Constructor that takes an explicit {@link XivelyClient instance}.
     *
     * @param client
     */
    public ProductService(XivelyClient client) {
        super(client);
    }

    /**
     * Get a product by ID
     *
     * @param id
     * @return
     * @throws IOException
     */
    public Product get(String id) throws IOException {
        checkDomainObjectId(id, "Product");

        StringBuilder uri = new StringBuilder(this.client.getBaseUri());
        uri.append("/").append(XivelyConstants.SEGMENT_PRODUCTS);
        uri.append("/").append(id);

        XivelyRequest request = new XivelyRequest();
        request.setUri(uri.toString()).setType(Product.class);

        XivelyResponse response = this.client.get(request);

        return (Product) response.getDomainObject();
    }

    /**
     * 
     * @param product
     * @return
     * @throws IOException
     */
    public Product update(Product product) throws IOException {
        checkDomainObject(product, "Product");

        String id = product.getId();

        this.logger.info(id);

        checkDomainObjectId(id, "Product");

        StringBuilder uri = new StringBuilder(this.client.getBaseUri());
        uri.append("/").append(XivelyConstants.SEGMENT_PRODUCTS);
        uri.append("/").append(id);

        XivelyRequest request = new XivelyRequest();
        request.setUri(uri);
        request.setObject(product);

        this.logger.info(request.toString());

        return (Product) this.client.put(request).getDomainObject();
    }
}
