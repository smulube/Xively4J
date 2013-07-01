// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.reflect.TypeToken;
import com.xively.client.XivelyClient;
import com.xively.client.XivelyConstants;
import com.xively.client.http.XivelyRequest;
import com.xively.client.http.XivelyResponse;
import com.xively.client.models.Product;
import com.xively.client.models.Product.State;

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
     *
     * @param product
     * @return
     * @throws IOException
     */
    public Product create(Product product) throws IOException {
        checkDomainObject(product, "Product");

        StringBuilder uri = new StringBuilder(this.client.getBaseUri());
        uri.append("/").append(XivelyConstants.SEGMENT_PRODUCTS);

        XivelyRequest request = new XivelyRequest();
        request.setUri(uri);
        request.setObject(product);

        XivelyResponse response = this.client.post(request);

        product = (Product) response.getBody();
        product.setId(response.getIdFromLocation());

        return product;
    }

    /**
     * Delete a product by id.
     *
     * @param id
     * @throws IOException
     */
    public void delete(String id) throws IOException {
        checkDomainObjectId(id, "Product");

        StringBuilder uri = new StringBuilder(this.client.getBaseUri());
        uri.append("/").append(XivelyConstants.SEGMENT_PRODUCTS);
        uri.append("/").append(id);

        XivelyRequest request = new XivelyRequest();
        request.setUri(uri);

        this.client.delete(request);
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
        request.setUri(uri).setType(Product.class);

        XivelyResponse response = this.client.get(request);

        return (Product) response.getBody();
    }

    /**
     * Get all products accessible by the current user.
     *
     * @param state
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public List<Product> list(State state) throws IOException {
        StringBuilder uri = new StringBuilder(this.client.getBaseUri());
        uri.append("/").append(XivelyConstants.SEGMENT_PRODUCTS);

        XivelyRequest request = new XivelyRequest();

        request.setUri(uri).setType(Product.class);

        request.setCollectionType(new TypeToken<List<Product>>() {
        }.getType());

        if (state != null) {
            request.addParam("state", state.getValue());
        }

        this.logger.info(request.toString());
        this.logger.info(request.generateUri());

        XivelyResponse response = this.client.get(request);

        return (List<Product>) response.getBody();
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

        checkDomainObjectId(id, "Product");

        StringBuilder uri = new StringBuilder(this.client.getBaseUri());
        uri.append("/").append(XivelyConstants.SEGMENT_PRODUCTS);
        uri.append("/").append(id);

        XivelyRequest request = new XivelyRequest();
        request.setUri(uri);
        request.setObject(product);

        return (Product) this.client.put(request).getBody();
    }
}
