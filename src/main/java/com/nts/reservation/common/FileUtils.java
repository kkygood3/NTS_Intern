package com.nts.reservation.common;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import com.nts.reservation.dto.download.DownloadInfo;
import com.nts.reservation.property.CommonProperties;

public class FileUtils {
	public static void setDownloadResponse(DownloadInfo donwloadInfo, HttpServletResponse response) {
		int fileLength = 0;
		String fileName = donwloadInfo.getFileName();

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", donwloadInfo.getContentType());
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		String fileDir = CommonProperties.ROOT_DIR_COMMNET_IMAGE + fileName;

		try (FileInputStream fis = new FileInputStream(fileDir);
			OutputStream out = response.getOutputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
				fileLength += readCount;
			}
			response.setHeader("Content-Length", "" + fileLength);
		} catch (Exception ex) {
			throw new RuntimeException("file Load Error");
		}
	}

	public static String addRandomSuffix(String fileName) {
		Random rng = new Random();
		String[] splitedStr = fileName.split("\\.");

		StringBuilder builder = new StringBuilder(splitedStr[0]);
		builder.append('_');
		for (int i = 0; i < CommonProperties.RANDOM_SUFFIX_LENGTH; i++) {
			builder.append((char)((int)(rng.nextInt(26)) + 97));
		}
		builder.append('.');
		builder.append(splitedStr[1]);

		return builder.toString();
	}
}
