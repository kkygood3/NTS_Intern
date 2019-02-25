package com.nts.reservation.common;

import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.nts.reservation.property.CommonProperties;

public class FileUtils {
	public static void setResponseHeader(String fileName, String fileDir, HttpServletResponse response) {
		String extension = getExtension(fileName);
		String contentType = "image/" + extension;
		
		int fileLength = CommonProperties.MAX_FILE_LENGTH;
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", contentType);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		response.setHeader("Content-Length", "" + fileLength);
		
		try (
			FileInputStream fis = new FileInputStream(fileDir);
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
	
	private static String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
}
