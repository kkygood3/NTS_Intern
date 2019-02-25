/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.file.service.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.common.exception.InternalServerErrorException;
import com.nts.reservation.file.dao.FileDao;
import com.nts.reservation.file.model.FileInfo;
import com.nts.reservation.file.service.FileService;

@Service
public class FileServiceLogic implements FileService {

	private static final int BUFFER_SIZE = 1024;

	@Autowired
	private FileDao fileDao;

	@Override
	public int storeMultipartFile(MultipartFile multipartFile, String directory) {
		String savePath = FILE_ROOT_DIRECTORY + directory;
		String fileName = multipartFile.getOriginalFilename();
		String newFilePath = savePath + fileName;

		File file = new File(newFilePath);

		try (
			InputStream input = multipartFile.getInputStream();
			FileOutputStream output = new FileOutputStream(file);) {
			byte[] buffer = new byte[BUFFER_SIZE];

			while (input.read(buffer) > 0) {
				output.write(buffer);
			}

		} catch (IOException e) {
			throw new InternalServerErrorException("file save fail");
		}

		FileInfo fileInfo = new FileInfo(multipartFile, directory);

		return fileDao.insertFileInfo(fileInfo);
	}

	@Override
	public File getFile(String filePath) {
		return new File(FILE_ROOT_DIRECTORY + filePath);
	}

}
