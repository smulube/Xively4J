// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.utils;

import java.io.Reader;
import java.lang.reflect.Type;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.xively.client.models.Location;
import com.xively.client.models.Location.Disposition;
import com.xively.client.models.Location.Domain;
import com.xively.client.models.Location.Exposure;
import com.xively.client.models.Unit;
import com.xively.client.models.Unit.IFCClassification;

/**
 * @author sam
 *
 */
public abstract class JsonUtils {

	private static final Gson gson = createGson();

	private static class IFCClassificationSerializer implements
			JsonSerializer<Unit.IFCClassification> {

		/*
		 * (non-Javadoc)
		 *
		 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object,
		 * java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
		 */
		@Override
		public JsonElement serialize(IFCClassification unitType,
				Type typeOfSrc, JsonSerializationContext context) {
			return new JsonPrimitive(unitType.getValue());
		}
	}

	private static class IFCClassificationDeserializer implements
			JsonDeserializer<Unit.IFCClassification> {

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

	}

	private static class DomainSerializer implements
			JsonSerializer<Location.Domain> {

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

	private static class DomainDeserializer implements
			JsonDeserializer<Location.Domain> {

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

	}

	private static class DispositionSerializer implements
			JsonSerializer<Location.Disposition> {

		/*
		 * (non-Javadoc)
		 *
		 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object,
		 * java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
		 */
		@Override
		public JsonElement serialize(Disposition disposition, Type domainType,
				JsonSerializationContext context) {
			return new JsonPrimitive(disposition.getValue());
		}

	}

	private static class DispositionDeserializer implements
			JsonDeserializer<Location.Disposition> {

		/*
		 * (non-Javadoc)
		 *
		 * @see
		 * com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement
		 * , java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
		 */
		@Override
		public Disposition deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			return Location.Disposition.fromString(json.getAsJsonPrimitive()
					.getAsString());
		}

	}

	private static class ExposureSerializer implements
			JsonSerializer<Location.Exposure> {

		/*
		 * (non-Javadoc)
		 *
		 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object,
		 * java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
		 */
		@Override
		public JsonElement serialize(Exposure exposure, Type domainType,
				JsonSerializationContext context) {
			return new JsonPrimitive(exposure.getValue());
		}

	}

	private static class ExposureDeserializer implements
			JsonDeserializer<Location.Exposure> {

		/*
		 * (non-Javadoc)
		 *
		 * @see
		 * com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement
		 * , java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
		 */
		@Override
		public Exposure deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			return Location.Exposure.fromString(json.getAsJsonPrimitive()
					.getAsString());
		}

	}

	/**
	 * Create our Gson instance.
	 *
	 * @return
	 */
	public static final Gson createGson() {
		final GsonBuilder builder = new GsonBuilder();
		builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

		builder.registerTypeAdapter(Unit.IFCClassification.class,
				new IFCClassificationSerializer());
		builder.registerTypeAdapter(Unit.IFCClassification.class,
				new IFCClassificationDeserializer());
		builder.registerTypeAdapter(Location.Domain.class,
				new DomainSerializer());
		builder.registerTypeAdapter(Location.Domain.class,
				new DomainDeserializer());
		builder.registerTypeAdapter(Location.Disposition.class,
				new DispositionSerializer());
		builder.registerTypeAdapter(Location.Disposition.class,
				new DispositionDeserializer());
		builder.registerTypeAdapter(Location.Exposure.class,
				new ExposureSerializer());
		builder.registerTypeAdapter(Location.Exposure.class,
				new ExposureDeserializer());

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
	 *
	 * @param reader
	 * @param type
	 * @return instance of type
	 */
	public static final <V> V fromJson(Reader reader, Class<V> type) {
		return gson.fromJson(reader, type);
	}

	/**
	 * Convert content of reader to given type
	 *
	 * @param reader
	 * @param type
	 * @return instance of type
	 */
	public static final <V> V fromJson(Reader reader, Type type) {
		return gson.fromJson(reader, type);
	}
}
