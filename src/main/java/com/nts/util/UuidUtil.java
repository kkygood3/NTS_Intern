/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author 전연빈
 */
public class UuidUtil {
	
	private static final String HASH_SHA_256 = "SHA-256";
	private static final String ENCODING_UTF_8 = "UTF-8";

	/**
	 * @desc uuid 가져오기
	 * @return uuid
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getUuid() throws NoSuchAlgorithmException, UnsupportedEncodingException {

		MessageDigest salt = MessageDigest.getInstance(HASH_SHA_256);
		salt.update(UUID.randomUUID().toString().getBytes(ENCODING_UTF_8));
		String digest = byteArrayToHex(salt.digest());
		return digest;

	}

	private static String byteArrayToHex(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer(bytes.length * 2);
		String hexNumber;
		for (int x = 0; x < bytes.length; x++) {
			hexNumber = "0" + Integer.toHexString(0xff & bytes[x]);
			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}
		return sb.toString();
	}

}
