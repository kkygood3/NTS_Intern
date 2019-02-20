package com.nts.reservation.compare;

import java.util.Comparator;
import java.util.Date;

import com.nts.reservation.dto.ReservationDisplayItem;

/**
 * 현재 날짜와 가까운 순서대로 정렬
 * @author si yoon
 *
 */
public class AscendingReservDateFromNow implements Comparator<ReservationDisplayItem> {
	@Override
	public int compare(ReservationDisplayItem r1, ReservationDisplayItem r2) {
		long now = new Date().getTime();
		long d1 = abs(r1.getReservationDate().getTime() - now);
		long d2 = abs(r2.getReservationDate().getTime() - now);
		return (int)(d1 - d2);
	}
	private long abs (long num) {
		return num > 0 ? num : num*-1;
	}
}
