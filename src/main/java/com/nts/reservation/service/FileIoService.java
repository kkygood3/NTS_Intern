/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dto.FileDto;

/**
 * @author 육성렬
 */
public interface FileIoService {

	public FileDto writeMultipartFile(String path, MultipartFile file) throws IOException;

	public void removeFilesForRollback(List<FileDto> files);
}
