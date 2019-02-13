package com.nts.reservation.service;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */
import java.util.List;

import com.nts.reservation.dto.reservation.Reservation;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */

public interface ReservationService {
	List<Reservation> getReservations(String email);

	List<Reservation> postReservations(String email);
}
