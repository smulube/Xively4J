// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.utils;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.xively.client.models.Channel;
import com.xively.client.models.Device;
import com.xively.client.models.Feed;
import com.xively.client.models.Location;
import com.xively.client.models.Product;
import com.xively.client.models.Unit;
import com.xively.client.utils.json.DispositionAdapter;
import com.xively.client.utils.json.DomainAdapter;
import com.xively.client.utils.json.ExposureAdapter;
import com.xively.client.utils.json.IFCClassificationAdapter;
import com.xively.client.utils.json.ProductStateAdapter;

/**
 * @author sam
 *
 */
public abstract class JsonUtils {

    private static final Logger logger = LoggerFactory
            .getLogger(JsonUtils.class);

    private static final Gson GSON = createGson();

    private static final Type PRODUCT_LIST_TYPE = new TypeToken<List<Product>>() {
    }.getType();

    private static final Type DEVICE_LIST_TYPE = new TypeToken<List<Device>>() {
    }.getType();

    /**
     * Create our Gson instance.
     *
     * @return
     */
    public static final Gson createGson() {
        final GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

        builder.registerTypeAdapter(Unit.IFCClassification.class,
                new IFCClassificationAdapter());
        builder.registerTypeAdapter(Location.Domain.class, new DomainAdapter());
        builder.registerTypeAdapter(Location.Disposition.class,
                new DispositionAdapter());
        builder.registerTypeAdapter(Location.Exposure.class,
                new ExposureAdapter());
        builder.registerTypeAdapter(Product.State.class,
                new ProductStateAdapter());

        return builder.create();
    }

    /**
     * Convert content of reader to given type
     *
     * @param reader
     * @param type
     * @return instance of type
     */
    public static final <V> V fromJson(Reader reader, Class<V> type) {
        String rootNode = getRootNode(type);

        if (rootNode == null) {
            return GSON.fromJson(reader, type);
        } else {
            JsonParser parser = new JsonParser();
            JsonElement document = parser.parse(reader);
            JsonElement element = document.getAsJsonObject().get(rootNode);
            return GSON.fromJson(element, type);
        }
    }

    /**
     * Convert content of reader to given type
     *
     * @param reader
     * @param type
     * @return instance of type
     */
    public static final <V> V fromJson(Reader reader, Type type) {
        String rootNode = getRootNode(type);

        if (rootNode == null) {
            return GSON.fromJson(reader, type);
        } else {
            JsonParser parser = new JsonParser();
            JsonElement document = parser.parse(reader);
            JsonElement element = document.getAsJsonObject().get(rootNode);
            return GSON.fromJson(element, type);
        }
    }

    /**
     * Convert string to given type
     *
     * @param json
     * @param type
     * @return instance of type
     */
    public static final <V> V fromJson(String json, Class<V> type) {
        if (logger.isDebugEnabled()) {
            logger.debug("Parsing json: " + json);
        }

        String rootNode = getRootNode(type);

        if (rootNode == null) {
            return GSON.fromJson(json, type);
        } else {
            JsonParser parser = new JsonParser();
            JsonElement document = parser.parse(json);
            JsonElement element = document.getAsJsonObject().get(rootNode);
            return GSON.fromJson(element, type);
        }
    }

    /**
     * Convert string to given type
     *
     * @param json
     * @param type
     * @return instance of type
     */
    public static final <V> V fromJson(String json, Type type) {
        if (logger.isDebugEnabled()) {
            logger.debug("Parsing json: " + json);
        }

        String rootNode = getRootNode(type);

        if (rootNode == null) {
            return GSON.fromJson(json, type);
        } else {
            JsonParser parser = new JsonParser();
            JsonElement document = parser.parse(json);
            JsonElement element = document.getAsJsonObject().get(rootNode);
            return GSON.fromJson(element, type);
        }

        // if (rootNode == null) {
        // return GSON.fromJson(json, type);
        // } else {
        // JsonParser parser = new JsonParser();
        // JsonElement document = parser.parse(json);
        // JsonElement element = document.getAsJsonObject().get(rootNode);
        // return GSON.fromJson(element, type);
        // }
    }

    /**
     * Convert string containing array of values to a List of the specified
     * type.
     *
     * @param json
     * @param type
     * @return
     */
    // public static final <V> List<V> fromJsonList(String json, Class<V> type)
    // {
    // if (logger.isDebugEnabled()) {
    // logger.debug("Parsing: " + json);
    // }
    //
    // String rootNode = getListRootNode(type);
    //
    //
    // if (rootNode == null) {
    // return gson.fromJson(json, new TypeToken<List<>>() {}.getType());
    // }
    // if (type.equals(Product.class)) {
    // JsonParser parser = new JsonParser();
    // JsonElement document = parser.parse(json);
    // JsonElement products = document.getAsJsonObject().get("products");
    // return gson.fromJson(products, new TypeToken<List<Product>>() {
    // }.getType());
    // } else {
    // return null;
    // }
    // }

    /**
     * Get reusable pre-configured {@link Gson} instance.
     *
     * @return Gson instance
     */
    public static final Gson getGson() {
        return GSON;
    }

    /**
     * Convert object to json.
     *
     * We have to do some slightly unpleasant things in here to deal with the
     * somewhat variable JSON we need to send to the API.
     *
     * @param object
     * @return json string
     */
    public static final String toJson(final Object object) {
        JsonElement objectJson;

        JsonElement jsonElement = GSON.toJsonTree(object);

        if (object.getClass().equals(Feed.class)
                || object.getClass().equals(Channel.class)) {
            jsonElement.getAsJsonObject().addProperty("version", "1.0.0");
            objectJson = jsonElement;
        } else if (object.getClass().equals(Product.class)) {
            JsonObject container = new JsonObject();
            container.add("product", jsonElement);
            objectJson = container;
        } else {
            objectJson = jsonElement;
        }

        return GSON.toJson(objectJson);
    }

    /**
     * Return root node name that contains our list of actual values or null if
     * the list is at the top level of the list.
     *
     * @param type
     * @return
     */
    private static <T> String getListRootNode(Class<T> type) {
        if (type.equals(Product.class)) {
            return "products";
        } else if (type.equals(Device.class)) {
            return "devices";
        } else {
            return null;
        }
    }

    /**
     * Work around our funky JSON. Some classes need to have the root element
     * removed. Return null if JSON for the given type doesn't need to be
     * tweaked in this way.
     *
     * @param <T>
     *
     * @param type
     * @return
     */
    private static <T> String getRootNode(Class<T> type) {
        if (type.equals(Product.class)) {
            return "product";
        } else if (type.equals(Device.class)) {
            return "device";
        } else {
            return null;
        }
    }

    /**
     *
     * @param type
     * @return
     */
    private static String getRootNode(Type type) {
        if (type.equals(PRODUCT_LIST_TYPE)) {
            return "products";
        } else if (type.equals(DEVICE_LIST_TYPE)) {
            return "devices";
        } else {
            return null;
        }
    }
}
