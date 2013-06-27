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
import com.xively.client.models.Product;

/**
 * @author sam
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private XivelyClient client;

    @Mock
    private XivelyResponse response;

    private ProductService service;

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullArgument() {
        new ProductService(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNullProduct() throws IOException {
        this.service.create(null);
    }

    @Test
    public void createProduct() throws IOException {
        Product newProduct = new Product();
        newProduct.setId("newid");
        doReturn(newProduct).when(this.response).getDomainObject();
        Product product = new Product();
        product.setName("Product Batch");
        this.service.create(product);
        XivelyRequest request = new XivelyRequest();
        request.setUri("https://api.xively.com/v2/products").setObject(product);
        verify(this.client).post(request);
    }

    @Test
    public void defaultConstructor() {
        assertNotNull(new ProductService().getClient());
    }

    @Test
    public void deleteProduct() throws IOException {
        this.service.delete("123abc");
        XivelyRequest request = new XivelyRequest();
        request.setUri("https://api.xively.com/v2/products/123abc");
        verify(this.client).delete(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteProductWithEmptyId() throws IOException {
        this.service.delete("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteProductWithNullId() throws IOException {
        this.service.delete(null);
    }

    @Test
    public void getProduct() throws IOException {
        this.service.get("123");
        XivelyRequest request = new XivelyRequest();
        request.setUri("https://api.xively.com/v2/products/123");
        request.setType(Product.class);
        verify(this.client).get(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getProductWithEmptyId() throws IOException {
        this.service.get("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getProductWithNullId() throws IOException {
        this.service.get(null);
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        doReturn("https://api.xively.com/v2").when(this.client).getBaseUri();
        doReturn(this.response).when(this.client).get(any(XivelyRequest.class));
        doReturn(this.response).when(this.client).put(any(XivelyRequest.class));
        doReturn(this.response).when(this.client).post(any(XivelyRequest.class));

        this.service = new ProductService(this.client);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateEmptyProductId() throws IOException {
        Product product = new Product();
        product.setId("");
        this.service.update(product);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNullProduct() throws IOException {
        this.service.update(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNullProductId() throws IOException {
        Product product = new Product();
        this.service.update(product);
    }

    @Test
    public void updateProduct() throws IOException {
        Product product = new Product();
        product.setId("123");
        this.service.update(product);
        XivelyRequest request = new XivelyRequest();
        request.setUri("https://api.xively.com/v2/products/123").setObject(
                product);
        verify(this.client).put(request);
    }
}
