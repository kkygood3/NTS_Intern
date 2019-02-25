/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.reservation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.nts.dto.reservation.ReservationCommentParam;

public interface ReservationCommentService {
	int addComment(ReservationCommentParam reservationCommentParam) throws NoSuchAlgorithmException, FileNotFoundException, IOException;
}
