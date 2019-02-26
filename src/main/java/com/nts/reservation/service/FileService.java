package com.nts.reservation.service;

import com.nts.reservation.dto.FileInfo;

public interface FileService {
	FileInfo getFileInfoByReservationUserCommentImageId(long reservationUserCommentImageId);
}
