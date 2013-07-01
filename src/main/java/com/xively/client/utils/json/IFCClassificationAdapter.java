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
import com.xively.client.models.Unit;
import com.xively.client.models.Unit.IFCClassification;

/**
 * @author sam
 *
 */
public class IFCClassificationAdapter implements
		JsonDeserializer<Unit.IFCClassification>,
		JsonSerializer<Unit.IFCClassification> {
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement
	 * , java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public IFCClassification deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		return Unit.IFCClassification.fromString(json.getAsJsonPrimitive()
				.getAsString());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object,
	 * java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(IFCClassification unitType, Type typeOfSrc,
			JsonSerializationContext context) {
		return new JsonPrimitive(unitType.getValue());
	}
}