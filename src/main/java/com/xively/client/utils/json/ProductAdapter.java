// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.utils.json;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.xively.client.models.Product;

/**
 * @author sam
 *
 */
public class ProductAdapter implements JsonDeserializer<Product>,
		JsonSerializer<Product> {

	private final Logger logger = LoggerFactory.getLogger(ProductAdapter.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement,
	 * java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public Product deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		JsonObject productJson = json.getAsJsonObject().get("product")
				.getAsJsonObject();

		return new Product(productJson);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object,
	 * java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(Product product, Type domainType,
			JsonSerializationContext context) {

		JsonObject productJson = new JsonObject();
		productJson.addProperty("name", product.getName());
		productJson.addProperty("description", product.getDescription());
		productJson.add("feed_defaults",
				context.serialize(product.getFeedDefaults()));
		productJson.addProperty("state", product.getState());

		JsonObject json = new JsonObject();
		json.add("product", productJson);

		return json;
	}

}
