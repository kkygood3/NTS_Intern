/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.file.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	static final int BUFFER_SIZE = 1024;

	static final String FILE_ROOT_DIRECTORY = "/reservation/file/";
	static final String FILE_IMAGE_DIRECTORY = "img/";

	int storeMultipartFile(MultipartFile multipartFile, String directory);

	File getFile(String path);
}
