package com.nts.reservation.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileHandler {
	public static final String PROJECT_ROOT = "C:/Users/USER/git/pjt06/src/main/webapp/";
	
	public static void saveFile(MultipartFile file, String saveFileName) {
		try {
			file.transferTo(new File(PROJECT_ROOT + saveFileName));
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static String getDirectory(String path) {
		File dir = new File(PROJECT_ROOT + path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return path;
	}
}
