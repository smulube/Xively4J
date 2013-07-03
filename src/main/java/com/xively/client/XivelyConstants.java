// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client;

/**
 * @author sam
 *
 */
public interface XivelyConstants {

    String API_HOST = "api.xively.com";

    String PROTOCOL_HTTPS = "https";

    String API_URL = PROTOCOL_HTTPS + "://" + API_HOST;

    String SEGMENT_ACTIVATE = "activate";

    String SEGMENT_FEEDS = "feeds";

    String SEGMENT_DATASTREAMS = "datastreams";

    String SEGMENT_DATAPOINTS = "datapoints";

    String SEGMENT_PRODUCTS = "products";

    String SEGMENT_DEVICES = "devices";

    String CONTENT_TYPE_JSON = "application/json";

    String CHARSET_UTF8 = "UTF-8";

}
