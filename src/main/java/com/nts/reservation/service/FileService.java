package com.nts.reservation.service;

import com.nts.reservation.dto.primitive.FileInfoDto;

/**
 * 파일에 대한 서비스 인터페이스
 * @author jinwoo.bae
 */
public interface FileService {
	FileInfoDto getCommentImage(int id);
}
