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
public class FolderFileIO {
	public void createFolderIfNotExist(File folder) {
		System.out.println("folder Exists : " + folder.exists());
		if (!folder.exists()) {
			try {
				folder.mkdir();
			} catch (Exception e) {
				throw new RuntimeException("folder create error");
			}
		}
	}

	public void saveFile(String basePath, File folder, Long commentId, MultipartFile file) {
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
			if (folder.exists()) {
				try {
					folder.delete();
				} catch (Exception e) {
					throw new RuntimeException("folder delete Error");
				}
			}
			throw new RuntimeException("file save error");
		}
	}
}
