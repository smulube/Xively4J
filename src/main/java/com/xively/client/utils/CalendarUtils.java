// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class for parsing and generating the ISO8601 date strings used in the
 * Xively API.
 * 
 * **IMPORTANT** - note that currently Xively publishes timestamps ISO8601
 * format, but including microsecond precision. Because the native Java
 * Calendar/Date classes don't have any way of representing microseconds, this
 * parser currently truncates the microsecond part of any timestamps, i.e. a
 * time of `2013-12-03T23:22:14:928372Z` will be truncated to
 * `2013-12-03T23:22:14:928Z. This does mean that if for any reason your app is
 * pushing data with batched datapoints at microsecond interval you will
 * probably get garbage in response
 * 
 * @author sam
 * 
 */
public class CalendarUtils {

    private static Logger logger = LoggerFactory.getLogger(CalendarUtils.class);

    public static final String DATAPOINT_ISO8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    public static final String ISO8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";

    /**
     * 
     * @param timestamp
     * @return
     * @throws ParseException
     */
    public static Calendar parseTimestamp(String timestamp)
            throws ParseException {
        return parseTimestamp(timestamp, null);
    }

    /**
     * @param string
     * @param timeZone
     * @return
     * @throws ParseException
     */
    public static Calendar parseTimestamp(String timestamp, TimeZone timeZone)
            throws ParseException {
        if (logger.isDebugEnabled()) {
            logger.debug("Original timestamp: " + timestamp);
        }

        // Replace Z in string with equivalent
        timestamp = timestamp.replace("Z", "+00:00");

        // Truncate microseconds
        timestamp = timestamp.replaceAll("\\.(\\d{3})\\d{3}", ".$1");

        if (logger.isDebugEnabled()) {
            logger.debug("Formatted timestamp: " + timestamp);
        }

        SimpleDateFormat format;

        if (timestamp.indexOf(".") >= 0) {
            format = new SimpleDateFormat(DATAPOINT_ISO8601_FORMAT);
        } else {
            format = new SimpleDateFormat(ISO8601_FORMAT);
        }

        Calendar calendar = Calendar.getInstance();

        if (timeZone != null) {
            calendar.setTimeZone(timeZone);
        }

        calendar.setTime(format.parse(timestamp));

        return calendar;
    }

    /**
     * Serialize a {@link Calendar} instance into an ISO 8601 string suitable
     * for Xively. This does not include milliseconds in the output. To include
     * milliseconds use
     * {@link CalendarUtils#serializeCalendar(Calendar, boolean)}
     * 
     * @param calendar
     * @return
     */
    public static String serializeCalendar(Calendar calendar) {
        return serializeCalendar(calendar, false);
    }

    /**
     * Serialize a {@link Calendar} instance into an ISO 8601 string suitable
     * for for Xively. Set includeMillis to true to include milliseconds in the
     * generated string.
     * 
     * @param calendar
     * @param includeMillis
     * @return
     */
    public static String serializeCalendar(Calendar calendar,
            boolean includeMillis) {
        if (logger.isDebugEnabled()) {
            logger.debug("Serializing: " + calendar.toString());
        }

        SimpleDateFormat format;

        if (includeMillis) {
            format = new SimpleDateFormat(DATAPOINT_ISO8601_FORMAT);
        } else {
            format = new SimpleDateFormat(ISO8601_FORMAT);
        }

        format.setTimeZone(calendar.getTimeZone());

        return format.format(calendar.getTime());
    }
}
