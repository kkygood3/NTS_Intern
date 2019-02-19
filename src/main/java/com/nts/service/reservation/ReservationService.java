/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.reservation;

import com.nts.dto.reservation.ReservationData;
import com.nts.dto.reservation.ReservationInfos;
import com.nts.exception.DisplayInfoNullException;

public interface ReservationService {
	ReservationInfos getReservationInfoByReservationEmail(String reservationEmail) throws DisplayInfoNullException;
	int addReservation(ReservationData reservationData);
	int modifyReservationToCancelByReservationId(long reservationId);
}
