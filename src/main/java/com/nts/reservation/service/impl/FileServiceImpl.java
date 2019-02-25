package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dto.primitive.FileInfoDto;
import com.nts.reservation.mapper.FileMapper;
import com.nts.reservation.service.FileService;

/**
 * 파일 서비스 인터페이스 구현로직
 * @author jinwoo.bae
 */
@Service
@Transactional(readOnly = true)
public class FileServiceImpl implements FileService {
	@Autowired
	private FileMapper fileMapper;

	/**
	 * 상품평 이미지 파일정보 가져오기
	 */
	@Override
	public FileInfoDto getCommentImage(int id) {
		return fileMapper.selectFileByReservationId(id);
	}

}
