/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author 육성렬
 */
public class FileUtil {
	private static Random random = new Random();

	public static final String IMAGE_DEFAULT_PATH = "/img";

	public static String createRandomFilename() {
		UUID uuid = UUID.randomUUID();
		int endIndex = random.nextInt(10) + 6;
		return uuid.toString().substring(0, endIndex);
	}
}
