/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dao.FileDao;
import com.nts.reservation.dto.FileInfo;
import com.nts.reservation.service.FileIoService;

/**
* @author  : 이승수
*/
@Service
@PropertySource("classpath:application.properties")
public class FileIoServiceImpl implements FileIoService {
	@Autowired
	private FileDao fileDao;

	@Value("${fileDir}")
	private String fileDir;

	@Override
	public FileInfo getFileInfoByProductId(Integer fileId) {
		return fileDao.selectFileInfo(fileId);
	}

	@Override
	public int getFileIdByProductId(Integer productId) {
		return fileDao.selectFileIdByProductId(productId);
	}

	@Override
	public int getFileIdByDisplayInfoId(Integer displayInfoId) {
		return fileDao.selectFileIdByDisplayInfoId(displayInfoId);
	}

	@Override
	public int setFileInfo(FileInfo fileInfo) {
		return fileDao.insertFileInfo(fileInfo);
	}

	private String createRandomFileName() {
		Random random = new Random();
		Date now = new Date();
		return String.valueOf(now.toString().hashCode() + random.nextInt());
	}

	@Override
	public FileInfo downloadFile(MultipartFile file) throws IOException {
		String fileName = createRandomFileName() + file.getContentType().replaceAll("image/", ".");
		String saveFileName = "userCommentImage/" + fileName;

		File newFile = new File(fileDir + saveFileName);
		newFile.getParentFile().mkdirs();
		newFile.createNewFile();

		FileOutputStream fileOutputStream = new FileOutputStream(newFile);
		InputStream inputStream = file.getInputStream();

		int readCount = 0;
		byte[] readBuffer = new byte[1024];
		while ((readCount = inputStream.read(readBuffer)) != -1) {
			fileOutputStream.write(readBuffer, 0, readCount);
		}

		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileName(file.getOriginalFilename());
		fileInfo.setSaveFileName(saveFileName);
		fileInfo.setContentType(file.getContentType());

		return fileInfo;
	}
}
