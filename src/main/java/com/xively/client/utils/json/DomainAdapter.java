// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.utils.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.xively.client.models.Location;
import com.xively.client.models.Location.Domain;

/**
 * @author sam
 *
 */
public class DomainAdapter implements JsonDeserializer<Location.Domain>,
		JsonSerializer<Location.Domain> {
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement
	 * , java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public Domain deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		return Location.Domain.fromString(json.getAsJsonPrimitive()
				.getAsString());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object,
	 * java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(Domain domain, Type domainType,
			JsonSerializationContext context) {
		return new JsonPrimitive(domain.getValue());
	}
}
