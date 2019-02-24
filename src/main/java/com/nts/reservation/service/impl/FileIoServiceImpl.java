/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dto.FileDto;
import com.nts.reservation.dto.request.regex.RegexPattern;
import com.nts.reservation.exception.CustomFileNotFoundException;
import com.nts.reservation.service.FileIoService;
import com.nts.reservation.util.FileUtil;

/**
 * @author 육성렬
 */
@Service
@PropertySource("classpath:application.properties")
public class FileIoServiceImpl implements FileIoService {

	private final int BUFFER_SIZE = 1024;

	@Value("${fileDir}")
	private String fileDir;

	@Override
	public FileDto writeMultipartFile(String path, MultipartFile file) throws IOException {
		String randomFileName = FileUtil.createRandomFilename();
		String fileExtension = file.getContentType().replaceAll(RegexPattern.IMAGE_CONTENT_TYPE, ".");
		String fileName = randomFileName + fileExtension;
		String saveFileName = path + "/" + fileName;

		File newFile = new File(fileDir + saveFileName);
		newFile.getParentFile().mkdirs();
		newFile.createNewFile();

		FileOutputStream fileOutputStream = new FileOutputStream(newFile);
		InputStream inputStream = file.getInputStream();

		int readCount = 0;
		byte[] readBuffer = new byte[BUFFER_SIZE];
		while ((readCount = inputStream.read(readBuffer)) != -1) {
			fileOutputStream.write(readBuffer, 0, readCount);
		}

		return new FileDto(file.getContentType(), fileName, saveFileName.substring(1, saveFileName.length()));
	}

	@Override
	public void removeFilesForRollback(List<FileDto> files) {
		for (FileDto file : files) {
			File target = new File(fileDir + "/" + file.getSaveFileName());
			if (target.exists()) {
				target.delete();
			}
		}
	}

}
