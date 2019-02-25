package com.nts.reservation.dao;

import com.nts.reservation.dto.FileInfo;

public interface FileInfoMapper {
	/**
	 * 파일정보 등록
	 * @param fileInfo 파일정보
	 * @return 파일id
	 */
	int insertFileInfo(FileInfo fileInfo);
}
