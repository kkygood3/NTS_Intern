/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dto.FileInfo;

/**
* @author  : 이승수
*/
public interface FileIoService {
	public FileInfo getFileInfoByProductId(Integer fileId);

	public int getFileIdByProductId(Integer productId);

	public int getFileIdByDisplayInfoId(Integer displayInfoId);

	public int setFileInfo(FileInfo fileInfo);

	public FileInfo downloadFile(MultipartFile file) throws IOException;
}