package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.FileInfoMapper;
import com.nts.reservation.dto.FileInfo;
import com.nts.reservation.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileInfoMapper fileInfoDao;
	
	@Override
	public FileInfo getFileInfoByReservationUserCommentImageId(long reservationUserCommentImageId)	{
		return fileInfoDao.selectFileInfoByReservationUserCommentImageId(reservationUserCommentImageId);
	}
}
