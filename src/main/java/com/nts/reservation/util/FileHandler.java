package com.nts.reservation.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 처리하는 클래스
 * 
 * @author si yoon
 *
 */
public class FileHandler {
	private static final String BASE_PATH = "C:/tmp/";

	/**
	 * 멀티파트파일 저장
	 * 
	 * @param file         멀티파트파밀
	 * @param saveFileName 저장할 이름
	 */
	public static void saveFile(MultipartFile file, String saveFileName) {
		try {
			file.transferTo(new File(BASE_PATH + saveFileName));
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 디렉토리가 없으면 만든다
	 * 
	 * @param path 검사할 디렉토리
	 * @return 디렉토리 존재여부
	 */
	public static boolean createDirectoryIfNotExist(String path) {
		File dir = new File(BASE_PATH + path);
		return dir.exists() | dir.mkdirs();
	}
	
	public static String getFullPath(String saveFileName) {
		return BASE_PATH + saveFileName;
	}
}
