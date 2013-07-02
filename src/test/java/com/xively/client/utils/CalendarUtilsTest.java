// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.utils;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Test;

/**
 * @author sam
 * 
 */
public class CalendarUtilsTest {

    @Test
    public void nonUtcSerialization() {
        Calendar calendar = getCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("PST"));
        assertEquals("2012-11-09T12:56:42.282-08:00",
                CalendarUtils.serializeCalendar(calendar));
    }

    @Test
    public void parseNonUTCString() throws ParseException {
        Calendar calendar = CalendarUtils
                .parseTimestamp("2013-02-03T20:35:42.123456+04:00");
        assertEquals(calendar.get(Calendar.YEAR), 2013);
        assertEquals(calendar.get(Calendar.MONTH), Calendar.FEBRUARY);
        assertEquals(calendar.get(Calendar.DATE), 3);
        assertEquals(calendar.get(Calendar.HOUR_OF_DAY), 16);
        assertEquals(calendar.get(Calendar.MINUTE), 35);
        assertEquals(calendar.get(Calendar.SECOND), 42);
        assertEquals(calendar.get(Calendar.MILLISECOND), 123);
    }

    @Test
    public void parseNonUTCStringInTimezone() throws ParseException {
        Calendar calendar = CalendarUtils
                .parseTimestamp("2013-02-03T20:35:42.123456+04:00", TimeZone.getTimeZone("PST"));
        assertEquals(calendar.get(Calendar.YEAR), 2013);
        assertEquals(calendar.get(Calendar.MONTH), Calendar.FEBRUARY);
        assertEquals(calendar.get(Calendar.DATE), 3);
        assertEquals(calendar.get(Calendar.HOUR_OF_DAY), 8);
        assertEquals(calendar.get(Calendar.MINUTE), 35);
        assertEquals(calendar.get(Calendar.SECOND), 42);
        assertEquals(calendar.get(Calendar.MILLISECOND), 123);
    }

    @Test
    public void parseUTCString() throws ParseException {
        Calendar calendar = CalendarUtils
                .parseTimestamp("2013-01-03T10:02:22.123456Z");
        assertEquals(calendar.get(Calendar.YEAR), 2013);
        assertEquals(calendar.get(Calendar.MONTH), Calendar.JANUARY);
        assertEquals(calendar.get(Calendar.DATE), 3);
        assertEquals(calendar.get(Calendar.HOUR_OF_DAY), 10);
        assertEquals(calendar.get(Calendar.MINUTE), 2);
        assertEquals(calendar.get(Calendar.SECOND), 22);
        assertEquals(calendar.get(Calendar.MILLISECOND), 123);
    }

    @Test
    public void parseUTCStringInTimeZone() throws ParseException {
        Calendar calendar = CalendarUtils.parseTimestamp(
                "2013-01-03T10:02:22.123456Z", TimeZone.getTimeZone("PST"));
        assertEquals(calendar.get(Calendar.YEAR), 2013);
        assertEquals(calendar.get(Calendar.MONTH), Calendar.JANUARY);
        assertEquals(calendar.get(Calendar.DATE), 3);
        assertEquals(calendar.get(Calendar.HOUR_OF_DAY), 2);
        assertEquals(calendar.get(Calendar.MINUTE), 2);
        assertEquals(calendar.get(Calendar.SECOND), 22);
        assertEquals(calendar.get(Calendar.MILLISECOND), 123);
    }

    @Test
    public void utcSerialization() {
        Calendar calendar = getCalendar();
        String output = CalendarUtils.serializeCalendar(calendar);
        assertEquals("2012-11-09T12:56:42.282Z", output);
    }

    /**
     * @return
     */
    private Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2012);
        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
        calendar.set(Calendar.DATE, 9);
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 56);
        calendar.set(Calendar.SECOND, 42);
        calendar.set(Calendar.MILLISECOND, 282);
        return calendar;
    }
}
