/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import com.nts.reservation.dto.download.DownloadInfo;
import com.nts.reservation.property.Properties;

/**
 * 파일 업로드, 다운로드에 사용할 static method 클래스
 */
public class FileUtils {
	/**
	 * DownloadInfo 정보를 바탕으로 response 헤더에 다운로드 설정
	 * @param DownloadInfo
	 * @param HttpServletResponse
	 */
	public static void setDownloadResponse(DownloadInfo donwloadInfo, HttpServletResponse response) {
		String fileName = donwloadInfo.getFileName();
		String fileDir = Properties.ROOT_DIR_COMMNET_IMAGE + fileName;

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", donwloadInfo.getContentType());

		try (FileInputStream fis = new FileInputStream(fileDir);
			OutputStream out = response.getOutputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Load Error");
		}
	}

	/**
	 * 파일명 중복을 방지하기 위해 사용
	 * 파일명 맨 뒤에 무작위 소문자 알파벳을 붙인다 
	 * @param fileName
	 * @return new fileName
	 */
	public static String addRandomSuffix(String fileName) {
		Random rng = new Random();
		int lastDotPos = fileName.lastIndexOf('.');

		StringBuilder builder = new StringBuilder(fileName.substring(0, lastDotPos));
		builder.append('_');
		for (int i = 0; i < Properties.RANDOM_SUFFIX_LENGTH; i++) {
			builder.append((char)((int)(rng.nextInt(26)) + 97));
		}
		builder.append(fileName.substring(lastDotPos, fileName.length()));
		return builder.toString();
	}
}
