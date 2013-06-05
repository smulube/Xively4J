// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.utils;

import java.io.Reader;
import java.lang.reflect.Type;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * @author sam
 *
 */
public abstract class JsonUtils {

	private static final Gson gson = createGson();

	/**
	 * Create our Gson instance.
	 *
	 * @return
	 */
	public static final Gson createGson() {
		final GsonBuilder builder = new GsonBuilder();
		builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		return builder.create();
	}

	/**
	 * Get reusable pre-configured {@link Gson} instance.
	 *
	 * @return Gson instance
	 */
	public static final Gson getGson() {
		return gson;
	}

	/**
	 * Convert object to json
	 *
	 * @param object
	 * @return json string
	 */
	public static final String toJson(final Object object) {
		JsonElement jsonElement = gson.toJsonTree(object);
		jsonElement.getAsJsonObject().addProperty("version", "1.0.0");
		return gson.toJson(jsonElement);
	}

	/**
	 * Convert string to given type
	 *
	 * @param json
	 * @param type
	 * @return instance of type
	 */
	public static final <V> V fromJson(String json, Class<V> type) {
		return gson.fromJson(json, type);
	}

	/**
	 * Convert string to given type
	 *
	 * @param json
	 * @param type
	 * @return instance of type
	 */
	public static final <V> V fromJson(String json, Type type) {
		return gson.fromJson(json, type);
	}

	/**
	 * Convert content of reader to given type
	 * @param reader
	 * @param type
	 * @return instance of type
	 */
	public static final <V> V fromJson(Reader reader, Class<V> type) {
		return gson.fromJson(reader, type);
	}

	/**
	 * Convert content of reader to given type
	 * @param reader
	 * @param type
	 * @return instance of type
	 */
	public static final <V> V fromJson(Reader reader, Type type) {
		return gson.fromJson(reader, type);
	}
}
