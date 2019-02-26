/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.file;

import java.io.FileNotFoundException;

import com.nts.dto.file.FileInfo;

public interface FileService {
	static final String FILE_PATH = "/Users/USER/git/2019_1st_intern6/src/main/webapp/";
	
	FileInfo getFileInfoByCommentId(int commentId) throws FileNotFoundException;
}
