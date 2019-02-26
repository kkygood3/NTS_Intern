/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.exception;

import com.nts.reservation.constant.FilePath;

/**
 * 이미지 파일 없음 발생 익셉션
 * @author jinwoo.bae
 */
public class NoImageFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final String saveFileName;

	public NoImageFoundException(String saveFileName) {
		super("No Image found for " + saveFileName);
		this.saveFileName = saveFileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public String getNoImageFilePath() {
		return FilePath.IMG_PATH + "/no_image.png";
	}
}
