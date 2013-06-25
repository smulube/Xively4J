// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author sam
 *
 */
public class TestHelper {
	public static final String FIXTURE_BASE = "src/test/res/";

	public static String loadStringFromFile(String filename) {
		try {
			FileInputStream fileStream = null;

			try {
				fileStream = new FileInputStream(new File(FIXTURE_BASE
						+ filename));
				FileChannel fileChannel = fileStream.getChannel();
				MappedByteBuffer bb = fileChannel.map(
						FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
				return Charset.defaultCharset().decode(bb).toString();
			} finally {
				fileStream.close();
			}

		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}
}
