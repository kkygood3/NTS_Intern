package com.nts.reservation.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */
@Component
public class FileIO {
	public void createDirIfNotExist(File dir) {
		if (!dir.exists()) {
			try {
				dir.mkdir();
			} catch (Exception e) {
				throw new RuntimeException("folder create error");
			}
		}
	}

	public void saveFile(String basePath, Long commentId, MultipartFile file) {
		try (
			FileOutputStream fos = new FileOutputStream(
				basePath + "img_uploaded/" + commentId + "/"
					+ file.getOriginalFilename());
			InputStream is = file.getInputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file save error");
		}
	}
	
	public void deleteFile(File targetDir) {
		try {
			targetDir.delete();
		} catch (Exception ex) {
			throw new RuntimeException("folder delete Error");
		}
	}
}
