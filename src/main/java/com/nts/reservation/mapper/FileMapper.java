/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nts.reservation.dto.primitive.FileInfoDto;

/**
 * 파일 Mapper
 * @author jinwoo.bae
 */
@Mapper
public interface FileMapper {
	/**
	 * 파일등록
	 */
	void insertFile(FileInfoDto fileInfo);
}
