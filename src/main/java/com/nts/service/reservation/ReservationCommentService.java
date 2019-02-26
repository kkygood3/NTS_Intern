/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.reservation;

import java.rmi.ServerException;

import com.nts.dto.reservation.ReservationCommentParam;

public interface ReservationCommentService {
	static final String IMAGE_FILE_FOLDER_PATH = "img/";

	int addComment(ReservationCommentParam reservationCommentParam) throws ServerException;
}
