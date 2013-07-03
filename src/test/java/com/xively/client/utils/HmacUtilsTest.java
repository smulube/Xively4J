// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author sam
 *
 */
public class HmacUtilsTest {

    @Test
    public void activationCode() {
        String productSecret = "377f93e5649c31635191c0e22258f5a5744698d3";
        String deviceSerial = "0001";
        String hmac = "13bf6065634a624104cfa14a1f977bede2aa39d8";

        assertEquals(hmac, HmacUtils.activationCode(productSecret, deviceSerial));
    }

    @Test
    public void generatingHmac() {
        String key = "377f93e5649c31635191c0e22258f5a5744698d3";
        String value = "0001";
        String hmac = "13bf6065634a624104cfa14a1f977bede2aa39d8";

        assertEquals(hmac, HmacUtils.hmacSha1(key, value));
    }

    @Test
    public void generatingHmacViaGeneralMethod() {
        String key = "377f93e5649c31635191c0e22258f5a5744698d3";
        String value = "0001";
        String hmac = "13bf6065634a624104cfa14a1f977bede2aa39d8";

        assertEquals(hmac, HmacUtils.hmacSha("HmacSHA1", key, value));
    }

    @Test(expected = RuntimeException.class)
    public void invalidCrypto() {
        HmacUtils.hmacSha("foo", "key", "value");
    }
}
