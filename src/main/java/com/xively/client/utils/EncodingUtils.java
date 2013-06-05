// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.utils;

import java.io.UnsupportedEncodingException;

import com.xively.client.XivelyConstants;

/**
 * @author sam
 *
 */
public class EncodingUtils {
	/**
	 * Decode base64 encoded string
	 *
	 * @param content
	 * @return byte array
	 */
	public static final byte[] fromBase64(final String content) {
		return Base64.decode(content);
	}

	/**
	 * Base64 encode given byte array
	 *
	 * @param content
	 * @return byte array
	 */
	public static final String toBase64(final byte[] content) {
		return Base64.encodeBytes(content);
	}

	/**
	 * Base64 encode given byte array
	 *
	 * @param content
	 * @return byte array
	 */
	public static final String toBase64(final String content) {
		byte[] bytes;
		try {
			bytes = content.getBytes(XivelyConstants.CHARSET_UTF8);
		} catch (UnsupportedEncodingException e) {
			bytes = content.getBytes();
		}
		return toBase64(bytes);
	}
}
